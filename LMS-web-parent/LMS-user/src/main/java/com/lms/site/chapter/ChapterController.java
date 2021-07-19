/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.chapter;

import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.CourseMember;
import com.lms.site.course.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Admin
 */
@Controller
public class ChapterController {
    
    @Autowired
    ChapterService chapterService;
    
    @Autowired
    CourseService courseService;
    
    @GetMapping("/join")
    public String showChapter(@ModelAttribute("member") CourseMember member, Model model) {
        
        List<Chapter> chapterByCourseId = chapterService.getChapterByCourseId(member.getCourseId());
        
        model.addAttribute("chapter", chapterByCourseId);
        return "course/course_resource";
    }
}
