/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.exam;

import java.util.List;
import com.lms.admin.exam.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lms.commom.entity.Exam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LENOVO
 */
@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @GetMapping("/exam")
    public String showFirstPage(Model model) {

        return listByPage(1, model, null);
    }

    @GetMapping("/exam/page/{pageNum}")
    public String listByPage(@PathVariable("pageNum") int pageNum, Model model, @Param("keyword") String keyword) {
        Page<Exam> page = examService.listByPage(pageNum, keyword);
        List<Exam> listExams = page.getContent();

        long totalItem = page.getTotalElements();
        int totalPage = page.getTotalPages();

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("listExams", listExams);
        model.addAttribute("keyword", keyword);

        return "exam/exam";
    }

    @GetMapping("/create_exam")
    public String showCreateExam(Model model) {

        model.addAttribute("exam", new Exam());
        return "exam/create_exam";
    }

    @PostMapping("/create_exam")
    public String createExam(Exam exam, @RequestParam("available")String available, @RequestParam("due")String due) throws ParseException {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        System.out.println(simpleDateFormat.parse(due) + " " );
        
        return "aa";
    }

}
