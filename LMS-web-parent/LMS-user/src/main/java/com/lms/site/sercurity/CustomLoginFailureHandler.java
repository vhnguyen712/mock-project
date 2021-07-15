package com.lms.site.sercurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.lms.commom.entity.User;
import com.lms.site.user.UserService;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Autowired
	UserService service;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String email = request.getParameter("email");
		User user = service.getUserByEmail(email);
		
		if (user != null) {
			if(user.isStatus() && user.isAccountNonLocked()) {
				if(user.getFailedAttempt() < UserService.MAX_FAILED_ATTEMPT - 1) {
					service.increaseFailedAttempt(user);
				}else {
					service.lock(user);
					exception =  new LockedException("Your account has been locked	due to 3 failed attempts.It will be unclock"
							+ "after 24h");
				}
			}else if(!user.isAccountNonLocked()) {
				if(service.unclock(user)) {
					exception = new LockedException("Your account has been unclock,Please try again!");
				}
			}
		}
		
		super.setDefaultFailureUrl("/login?error");
		super.onAuthenticationFailure(request, response, exception);
	}

	
}
