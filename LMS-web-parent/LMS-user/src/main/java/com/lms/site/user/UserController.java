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
import com.lms.site.sercurity.MyUserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class UserController {
        
        @Autowired
        UserRepository userRepository;
        
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

	@GetMapping("/profile")
	public String viewProfile(@AuthenticationPrincipal MyUserDetail user, Model model) {
		model.addAttribute("user", user.getUser());
		return "profile";
	}
        
        
	@PostMapping("/edit_profile")
	public String editProfile(@ModelAttribute("user") User user, @AuthenticationPrincipal MyUserDetail userdl) {
                user.setId(userdl.getUser().getId());
                userService.updateUserProfile(user);
		return "/profile";
	}
}
