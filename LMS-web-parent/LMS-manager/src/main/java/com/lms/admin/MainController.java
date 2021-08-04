package com.lms.admin;

import com.lms.admin.manager.ManagerService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lms.admin.sercurity.MyManagerDetail;
import com.lms.commom.entity.Manager;
import com.lms.commom.entity.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    ManagerService managerService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
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
    public String viewProfile(@AuthenticationPrincipal MyManagerDetail user, Model model, HttpServletRequest request) {
        model.addAttribute("user", user.getManager());
        boolean check = managerService.checkAuthenticationType(request);
        model.addAttribute("check", check);
        return "profile";
    }

    @PostMapping("/edit_profile")
    public String editProfile(@ModelAttribute("user") Manager user, @AuthenticationPrincipal MyManagerDetail userdl, Model model, HttpServletRequest request) {

        user.setId(userdl.getManager().getId());
        managerService.updateUserProfile(user);
        boolean check = managerService.checkAuthenticationType(request);

        model.addAttribute("SUCCESS", true);
        model.addAttribute("check", check);

        return "profile";
    }

    @PostMapping("/change_password")
    public String changePassword(@AuthenticationPrincipal MyManagerDetail userdl, HttpServletRequest request, Model model) {
        String current = userdl.getPassword();
        String oldpass = request.getParameter("oldpassword");
        String newpass = request.getParameter("newpassword");
        String confirm = request.getParameter("confirmpassword");
        boolean check = managerService.checkAuthenticationType(request);
        if (passwordEncoder.matches(oldpass, current)) {
            if (newpass.equals(confirm)) {
                String encode = passwordEncoder.encode(newpass);
                managerService.changePassword(encode, userdl.getManager().getId());
                SecurityContextHolder.clearContext();
                model.addAttribute("SUCCESS", true);
                return "login";
            } else {
                model.addAttribute("user", userdl.getManager());
                model.addAttribute("ERROR", "Your confirm password doesn't match, pls try again !");
                model.addAttribute("check", check);
                return "profile";
            }
        }
        model.addAttribute("user", userdl.getManager());
        model.addAttribute("ERROR", "Your current password is wrong, pls try again !");
        model.addAttribute("check", check);
        return "profile";

    }
}
