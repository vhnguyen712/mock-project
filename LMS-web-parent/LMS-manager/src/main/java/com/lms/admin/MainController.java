package com.lms.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lms.admin.manager.MyManagerDetail;


@Controller
public class MainController {

	@GetMapping("")
	public String viewHome() {
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}
	
	@GetMapping("/profile")
	public String viewProfile(@AuthenticationPrincipal MyManagerDetail user, Model model) {
		model.addAttribute("user", user);
		return "profile";
	}
}
