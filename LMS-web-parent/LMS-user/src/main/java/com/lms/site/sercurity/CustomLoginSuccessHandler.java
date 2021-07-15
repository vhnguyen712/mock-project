package com.lms.site.sercurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.lms.commom.entity.User;
import com.lms.site.user.UserService;


@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	UserService service;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		MyUserDetail detail = (MyUserDetail) authentication.getPrincipal();
		User user = detail.getUser();
		
		if(user.getFailedAttempt() > 0) {
			service.resetFailedAttempt(user.getEmail());
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	

}
