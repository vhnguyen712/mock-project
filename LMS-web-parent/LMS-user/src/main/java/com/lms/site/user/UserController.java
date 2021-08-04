package com.lms.site.user;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lms.commom.entity.User;
import com.lms.site.Utility;
import com.lms.site.sercurity.MyUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {

        model.addAttribute("user", new User());

        return "register/register";
    }

    @PostMapping("/create_user")
    public String createCustomer(User user, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        userService.registerUser(user);
        String siteURL = Utility.getSite(request);

        userService.sendVerificationEmail(user, siteURL);

        return "register/register_success";
    }

    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code, Model model) {
        boolean verify = userService.verify(code);

        return "register/" + (verify ? "verify_success" : "verify_fail");
    }

    @GetMapping("/profile")
    public String viewProfile(Model model, HttpServletRequest request) {
    	
    	String email = userService.getEmailOfAuthenticatedUser(request);
    	boolean check = userService.checkAuthenticationType(request);
    	User user = userService.getUserByEmail(email);
    	
    	model.addAttribute("user", user);
        model.addAttribute("check", check);
    	
        return "profile";
    }
    


    @PostMapping("/edit_profile")
    public String editProfile(@ModelAttribute("user") User user, @AuthenticationPrincipal MyUserDetail userdl, Model model, HttpServletRequest request) {
        
        user.setId(userdl.getUser().getId());
        userService.updateUserProfile(user);
        boolean check = userService.checkAuthenticationType(request);
        
        model.addAttribute("SUCCESS", true);
        model.addAttribute("check", check);
                
        return "profile";
    }

    @PostMapping("/change_password")
    public String changePassword(@AuthenticationPrincipal MyUserDetail userdl, HttpServletRequest request, Model model) {
        String current = userdl.getPassword();
        String oldpass = request.getParameter("oldpassword");
        String newpass = request.getParameter("newpassword");
        String confirm = request.getParameter("confirmpassword");
        boolean check = userService.checkAuthenticationType(request);
        if (passwordEncoder.matches(oldpass, current)) {
            if (newpass.equals(confirm)) {
                String encode = passwordEncoder.encode(newpass);
                userService.changePassword(encode, userdl.getUser().getId());
                SecurityContextHolder.clearContext();
                model.addAttribute("SUCCESS", true);
                return "login";
            } else {
                model.addAttribute("user",userdl.getUser());
                model.addAttribute("ERROR", "Your confirm password doesn't match, pls try again !");
                model.addAttribute("check", check);
                return "profile";
            }
        }
        model.addAttribute("user",userdl.getUser());
        model.addAttribute("ERROR", "Your current password is wrong, pls try again !");
        model.addAttribute("check", check);
        return "profile";

    }
}
