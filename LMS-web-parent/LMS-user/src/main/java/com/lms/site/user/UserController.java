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
    	
    	User user = userService.getUserByEmail(email);
    	
    	model.addAttribute("user", user);
    	
        return "profile";
    }
    


    @PostMapping("/edit_profile")
    public String editProfile(@ModelAttribute("user") User user, @AuthenticationPrincipal MyUserDetail userdl, Model model) {
        user.setId(userdl.getUser().getId());
        userService.updateUserProfile(user);
        model.addAttribute("SUCCESS", "Update profile successful !");
        return "/profile";
    }

    @PostMapping("/change_password")
    public String changePassword(@AuthenticationPrincipal MyUserDetail userdl, HttpServletRequest request, Model model) {
        String current = userdl.getPassword();
        String oldpass = request.getParameter("oldpassword");
        String newpass = request.getParameter("newpassword");
        String confirm = request.getParameter("confirmpassword");
        if (passwordEncoder.matches(oldpass, current)) {
            if (newpass.equals(confirm)) {
                String encode = passwordEncoder.encode(newpass);
                userService.changePassword(encode, userdl.getUser().getId());
                model.addAttribute("SUCCESS", "Chasnge password successful !");
                return "index";
            } else {
                model.addAttribute("ERROR", "Confirm password not match, change's not saved.");
                return "index";
            }
        }
        model.addAttribute("ERROR", "Your current password is wrong, change's not saved.");
        return "index";

    }
}
