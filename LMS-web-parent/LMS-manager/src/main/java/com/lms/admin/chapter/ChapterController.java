package com.lms.admin.chapter;

import com.lms.admin.course.CourseService;
import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ChapterController {

    @GetMapping("/add_chapter")
    public String showAddChapterForm() {
        return "course/resource/add_chapter_form";
    }

    @Autowired
    ChapterService chapterService;

    @Autowired
    CourseService courseService;

    @GetMapping("/join")
    public String showChapter(@ModelAttribute("course") Course course, Model model) {

        List<Chapter> chapterByCourseId = chapterService.getChapterByCourseId(course.getId());

        model.addAttribute("chapter", chapterByCourseId);
        return "course/course_resource";
    }
}
