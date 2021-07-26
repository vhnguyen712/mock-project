package com.lms.site.sercurity.oauth;

import com.lms.site.user.UserService;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.lms.commom.entity.AuthenticationType;
import com.lms.commom.entity.User;

@Component
public class Oauth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		MyOauth2User oauth2User = (MyOauth2User) authentication.getPrincipal();
		String name = oauth2User.getName();
		String email = oauth2User.getEmail();
		User user = userService.getUserByEmail(email);
		String clientName = oauth2User.getClientName();


		AuthenticationType authenticationType = getAuthenticationType(clientName);
		
		if (user == null) {
			userService.addCustomerUponOauth2Login(name, email,authenticationType);
		} else {
			userService.updateAuthenticationType(user, authenticationType);
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private AuthenticationType getAuthenticationType(String clientName) {
		if (clientName.equals("Google")) {
			return AuthenticationType.GOOGLE;
		}else if (clientName.equals("Facebook")) {
			return AuthenticationType.FACEBOOK;
		}else {
			return AuthenticationType.DATABASE;
		}
	}
}
