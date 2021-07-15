package com.lms.site.user;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lms.commom.entity.User;
import com.lms.site.Utility;


@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		
		model.addAttribute("user", new User());
		
		return "register/register";
	}
	
	@PostMapping("/create_user")
	public String createCustomer(User user,HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		userService.registerUser(user);
		String siteURL = Utility.getSite(request);

		userService.sendVerificationEmail(user, siteURL);
		
		return "register/register_success";
	}
	
	@GetMapping("/verify")
	public String verifyAccount(@Param("code")String code, Model model) {
		boolean verify = userService.verify(code);
		
		return "register/" + (verify ? "verify_success" : "verify_fail");
	}


}
