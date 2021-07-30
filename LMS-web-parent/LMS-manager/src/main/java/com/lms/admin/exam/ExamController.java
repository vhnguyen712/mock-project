/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.exam;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.admin.course.CourseService;
import com.lms.admin.manager.ManagerService;
import com.lms.admin.sercurity.MyManagerDetail;
import com.lms.commom.entity.Course;
import com.lms.commom.entity.Exam;
import com.lms.commom.entity.Manager;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author LENOVO
 */
@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    CourseService courseService;

    @Autowired
    ManagerService managerService;

    @GetMapping("/view_exam/{course_id}")
    public String showFirstPage(Model model,@PathVariable("course_id")int id) {
      
        return listByPage(1, model, null,id);
    }

    @GetMapping("/view_exam/{course_id}/page/{pageNum}")
    public String listByPage(@PathVariable("pageNum") int pageNum, Model model, @Param("keyword") String keyword, int id) {
        Page<Exam> page = examService.listByPage(id,pageNum, keyword);
        List<Exam> listExams = page.getContent();
       

        long totalItem = page.getTotalElements();
        int totalPage = page.getTotalPages();

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("listExams", listExams);
        model.addAttribute("keyword", keyword);

        return "exam/view_exam";
    }

    @GetMapping("/create_exam")
    public String showCreateExam(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MyManagerDetail managerDetail = (MyManagerDetail) authentication.getPrincipal();

        int manager_id = managerDetail.getId();

        List<Course> listCoursesOfTeacher = courseService.listAllCourseOfTeacher(manager_id);

        model.addAttribute("listCoursesOfTeacher", listCoursesOfTeacher);
        model.addAttribute("exam", new Exam());

        return "exam/create_exam";
    }

    @PostMapping("/create_exam")
    public String createExam(Exam exam, @RequestParam("due") String due, @RequestParam("available") String available,
            HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MyManagerDetail managerDetail = (MyManagerDetail) authentication.getPrincipal();

        int manager_id = managerDetail.getId();

        Manager manager = managerService.getManagerById(manager_id);

        String name = request.getParameter("idCourse");

        Course course = courseService.findCourseByName(name);

        exam.setManager(manager);
        exam.setCourse(course);
        exam.setDue(due);
        exam.setAvailable(available);
        exam.setCreateDate(new Date());

        examService.saveExam(exam);

        return "redirect:/";
    }

//    @GetMapping("/join1")
//
//    public String showExam(@ModelAttribute("course") Course course, Model model) {
//        List<Exam> ExamByCourse = examService.getExamByCourseId(course.getId());
//
//        model.addAttribute("exam", ExamByCourse);
//        return "exam/view_exam";
//    }
}
