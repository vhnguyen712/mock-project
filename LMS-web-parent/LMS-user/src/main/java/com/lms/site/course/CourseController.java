/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.course;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lms.commom.entity.Course;
import com.lms.commom.entity.CourseMember;
import com.lms.commom.entity.User;
import com.lms.site.sercurity.MyUserDetail;

/**
 *
 * @author LENOVO
 */
@Controller
public class CourseController {
    
    @Autowired
    CourseService courseService;
    
    @Autowired
    CourseMemberService memberService;
    
    @GetMapping("/course")
    public String showCourse(Model model, Authentication authentication){
        MyUserDetail userD = (MyUserDetail) authentication.getPrincipal();
        
        User user = userD.getUser();
        
        List<Course> listCourse = courseService.findAllCourse();
        
        CourseMember member = new CourseMember();
        model.addAttribute("member", member);
        model.addAttribute("user", user);
        if(listCourse!=null)
        {
        model.addAttribute("listCourse", listCourse);
        }else{
            model.addAttribute("Empty", "No course found.");
        }
        return "course/course";
    }

    
    @PostMapping("/attend")
    public String attendCourse(@ModelAttribute("member") CourseMember member) throws UnsupportedEncodingException, MessagingException  {
        memberService.attend(member);
        return "redirect:/course";
    }
    
    @GetMapping("/mycourse")
    public String showMyCourse(Model model, Authentication authentication) {
        return "course/my_course";
    }
    
}
