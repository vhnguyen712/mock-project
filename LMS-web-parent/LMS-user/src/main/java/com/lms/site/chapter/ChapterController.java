/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.chapter;

import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Admin
 */
@Controller
public class ChapterController {
    
    @Autowired
    ChapterService chapterService;
    
    @GetMapping("/join")
    public String showChapter(Model model) {
        List<Chapter> chapterByCourseId = chapterService.getChapterByCourseId(1);
        
        for (Chapter chapter : chapterByCourseId) {
            System.out.println(chapter.getName());
        }
        
        model.addAttribute("chapter", chapterByCourseId);
        return "course/course_resource";
    }
}
