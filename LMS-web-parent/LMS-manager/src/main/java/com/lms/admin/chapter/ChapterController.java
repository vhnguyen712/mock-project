package com.lms.admin.chapter;

import com.lms.admin.course.CourseService;
import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.Course;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChapterController {
    
    @Autowired
    CourseService courseService;
    
    @Autowired
    ChapterService chapterService;
    
    @GetMapping("/add_chapter/{id}")
    public String showAddChapterForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("chapter", new Chapter());
        int courseid = Integer.parseInt(id);
        Course course = courseService.findCourseById(courseid);
        model.addAttribute("course", course);
        return "course/resource/add_chapter_form";
    }
    
    @PostMapping("/add_chapter")
    public String createChapter(Chapter chapter, HttpServletRequest request ){
        int id = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseService.findCourseById(id);
        chapterService.saveChapter(chapter, course);
        return "redirect:/teacher_course";
    }
    
}
