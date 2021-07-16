/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lms.admin.manager.MyManagerDetail;
import com.lms.commom.entity.Course;

/**
 *
 * @author LENOVO
 */
@Controller
public class CourseController {
    
    @Autowired
    CourseService courseService;
    
    @GetMapping("/course")
    public String showCourse(Model model){
        List<Course> listCourse = courseService.findAllCourse();
        if(listCourse!=null)
        {
        model.addAttribute("listCourse", listCourse);
        }else{
            model.addAttribute("Empty", "No course found.");
        }
        return "course/course";
    }
    
    @GetMapping("/create_course")
    public String showCreateCourse(Model model) {
    	
    	model.addAttribute("course", new Course());
    	return "course/create_course";
    }
    
    @PostMapping("/create_course")
    public String createCourse(Course course) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	courseService.saveCourse(course,authentication);
    	
    	return "redirect:/course";
    }
    
    @GetMapping("/teacher_course")
    public String getTeacherCourse(Model model) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	MyManagerDetail managerDetail = (MyManagerDetail) authentication.getPrincipal();

		int manager_id = managerDetail.getId();
    	
    	List<Course> listCourse = courseService.listAllCourseOfTeacher(manager_id);
    	
    	model.addAttribute("listCourse", listCourse);
    	
    	return "course/teacher_course";
    }
}
