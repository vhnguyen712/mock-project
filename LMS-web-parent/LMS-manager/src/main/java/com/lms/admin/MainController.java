package com.lms.admin;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lms.admin.sercurity.MyManagerDetail;


@Controller
public class MainController {

	@GetMapping("")
	public String viewHome() {
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/profile")
	public String viewProfile(@AuthenticationPrincipal MyManagerDetail user, Model model) {
		model.addAttribute("user", user);
		return "profile";
	}
}
