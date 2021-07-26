package com.lms.site.user;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.commom.entity.AuthenticationType;
import com.lms.commom.entity.User;
import com.lms.site.sercurity.MyUserDetail;
import com.lms.site.sercurity.oauth.MyOauth2User;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class UserService {

    private static final long LOCK_TIME_DURATION = 10 * 60 * 1000;

    public static final int MAX_FAILED_ATTEMPT = 3;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender mailSender;

    public boolean isEmailUnique(String email) {
        User customer = userRepository.getUserByEmail(email);

        return customer == null;
    }

    public void registerUser(User user) {
        String encode = passwordEncoder.encode(user.getPass());

        user.setPass(encode);
        user.setStatus(false);
        user.setCreateTime(new Date());
        user.setAuthenticationType(AuthenticationType.DATABASE);
        user.setAccountNonLocked(true);

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);

        userRepository.save(user);

    }

    public void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {

        String sub = "Verify for your registration";
        String senderName = "Openolat";
        String content = "<p>Dear " + user.getName() + ",</p>";
        content += "<p> Click the link below to verify: </p>";

        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        content += "<h3><a href=\"" + verifyURL + "\">VERIFY</a></h3>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("datisme731@gmail.com", senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(sub);
        helper.setText(content, true);

        mailSender.send(message);
    }

    public boolean verify(String code) {
        User user = userRepository.findByVerificationCode(code);

        if (user == null || user.isStatus()) {
            return false;
        } else {
            userRepository.enable(user.getId());
            return true;
        }
    }

    public void increaseFailedAttempt(User user) {
        int newFailedAttempt = user.getFailedAttempt() + 1;
        userRepository.updateFailedAttempt(newFailedAttempt, user.getEmail());
    }

    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());

        userRepository.save(user);
    }

    public boolean unclock(User user) {
        long lockTime = user.getLockTime().getTime();
        long currentTime = System.currentTimeMillis();

        if (lockTime + LOCK_TIME_DURATION < currentTime) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);

            userRepository.save(user);

            return true;
        }
        return false;
    }

    public void resetFailedAttempt(String username) {
        userRepository.updateFailedAttempt(0, username);

    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public void updateUserProfile(User user) {
        userRepository.updateProfile(user.getName(), user.getPhone(), user.getId());
    }

    public void changePassword(String password, int id) {
        userRepository.changePassword(password, id);
    }

    public void addCustomerUponOauth2Login(String name, String email, AuthenticationType authenticationType) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setStatus(true);
        user.setCreateTime(new Date());
        user.setPass(null);
        user.setAuthenticationType(authenticationType);

        userRepository.save(user);

    }

    public void updateAuthenticationType(User user, AuthenticationType type) {

        if (!user.getAuthenticationType().equals(type)) {
            userRepository.updateAuthenticationType(user.getId(), type);
        }
    }

    public String getEmailOfAuthenticatedUser(HttpServletRequest request) {
        Object principal = request.getUserPrincipal();
        String email = null;

        if (principal instanceof UsernamePasswordAuthenticationToken || principal instanceof RememberMeAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
            MyUserDetail userDetail = (MyUserDetail) token.getPrincipal();
            email = userDetail.getEmail();
        } else if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            MyOauth2User oauth2User = (MyOauth2User) token.getPrincipal();
            email = oauth2User.getEmail();
        }

        return email;

    }

    public boolean checkAuthenticationType(HttpServletRequest request) {
        Object principal = request.getUserPrincipal();
        boolean check = true;
        if (principal instanceof UsernamePasswordAuthenticationToken || principal instanceof RememberMeAuthenticationToken) {
            check = true;
        } else if (principal instanceof OAuth2AuthenticationToken) {
            check = false;
        }
        return check;
    }
}
