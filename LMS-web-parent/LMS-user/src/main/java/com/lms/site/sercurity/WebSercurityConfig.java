package com.lms.site.sercurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lms.site.sercurity.oauth.MyOauth2UserService;
import com.lms.site.sercurity.oauth.Oauth2LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSercurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailService();
	}
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/course").authenticated()
				.anyRequest().permitAll()
			.and()
				.formLogin().loginPage("/login").failureHandler(failureHandler)
				.successHandler(successHandler).usernameParameter("email").permitAll()
			.and().oauth2Login().loginPage("/login").userInfoEndpoint().userService(myOauth2UserService)
			.and().successHandler(oauth2LoginSuccessHandler)
			.and()
				.logout().permitAll();
	}
	
	@Autowired
	private CustomLoginFailureHandler failureHandler;
	
	@Autowired
	private CustomLoginSuccessHandler successHandler;
	
	@Autowired
	private Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;
	
	@Autowired
	private MyOauth2UserService myOauth2UserService;
}
