/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.course;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String showFirstPage(Model model) {

        return listByPage(1, model, null);
    }

    @GetMapping("/course/page/{pageNum}")
    public String listByPage(@PathVariable("pageNum") int pageNum, Model model, @Param("keyword") String keyword) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MyUserDetail userD = (MyUserDetail) authentication.getPrincipal();

        User user = userD.getUser();

        Page<Course> page = courseService.listByPage(pageNum, keyword);
        List<Course> listCourses = page.getContent();

        CourseMember member = new CourseMember();
        model.addAttribute("member", member);
        model.addAttribute("user", user);

        if (listCourses != null) {
            model.addAttribute("listCourse", listCourses);
        } else {
            model.addAttribute("Empty", "No course found.");
        }

        long totalItem = page.getTotalElements();
        int totalPage = page.getTotalPages();

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("listCourses", listCourses);
        model.addAttribute("keyword", keyword);

        return "course/course";
    }

    @PostMapping("/attend")
    public String attendCourse(@ModelAttribute("member") CourseMember member)
            throws UnsupportedEncodingException, MessagingException {
        memberService.attend(member);
        return "redirect:/course";
    }

    @GetMapping("/mycourse")
    public String showMyCourse(Model model, Authentication authentication) {
        MyUserDetail userD = (MyUserDetail) authentication.getPrincipal();

        List<CourseMember> myCourse = memberService.getMyCourse(userD.getUser().getId());

        List<Course> listCourse = new ArrayList<>();
        for (CourseMember courseMember : myCourse) {
            listCourse.add(courseService.getCourse(courseMember.getCourseId()));
        }
        if (listCourse != null) {
            model.addAttribute("listCourse", listCourse);
        } else {
            model.addAttribute("Empty", "No course found.");
        }
        
        CourseMember member = new CourseMember();
        
        model.addAttribute("member", member);
        return "course/my_course";
    }

    @GetMapping("/join")
    public String joinCourse(@ModelAttribute("member") CourseMember member, Model model, Authentication authentication) {
        System.out.println(member.getCourseId());
        return "course/course-resource";
    }
}
