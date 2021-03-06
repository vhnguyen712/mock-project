package com.lms.admin.chapter;

import com.lms.admin.course.CourseService;
import com.lms.admin.exam.ExamRepository;
import com.lms.admin.exam.ExamService;
import com.lms.admin.resource.ResourceService;
import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.Course;
import com.lms.commom.entity.CourseMember;
import com.lms.commom.entity.Exam;
import com.lms.commom.entity.Resources;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import static org.hibernate.criterion.Projections.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChapterController {

    @Autowired
    CourseService courseService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    ExamService examService;

    @GetMapping("/add_chapter/{id}")
    public String showAddChapterForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("chapter", new Chapter());
        int courseid = Integer.parseInt(id);
        Course course = courseService.findCourseById(courseid);
        model.addAttribute("course", course);
        return "course/resource/add_chapter_form";
    }

    @PostMapping("/add_chapter")
    public String createChapter(Chapter chaptertmp, HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseService.findCourseById(id);
        chapterService.saveChapter(chaptertmp, course);
        List<Chapter> chapterByCourseId = chapterService.getChapterByCourseId(id);
        List<Exam> listExam = examService.getExamByCourse(course.getId());

        LinkedHashMap<Chapter, List<Resources>> map = new LinkedHashMap<>();

        for (Chapter chapter : chapterByCourseId) {
            System.out.println(chapter.getName());
            List<Resources> resourceByChapterId = resourceService.getResourceByChapterId(chapter.getId());

            map.put(chapter, resourceByChapterId);

        }
        //model Exam - ko xoa
        model.addAttribute("id", course.getId());

        model.addAttribute("chapter", map);
        return "course/course_resource.html";
    }

    @GetMapping("/join")
    public String showChapter(@ModelAttribute("course") Course course, Model model) {
        List<Chapter> chapterByCourseId = chapterService.getChapterByCourseId(course.getId());
        //List Exam - ko xoa
        List<Exam> listExam = examService.getExamByCourse(course.getId());

        LinkedHashMap<Chapter, List<Resources>> map = new LinkedHashMap<>();

        for (Chapter chapter : chapterByCourseId) {
            System.out.println(chapter.getName());
            List<Resources> resourceByChapterId = resourceService.getResourceByChapterId(chapter.getId());

            map.put(chapter, resourceByChapterId);

        }
        //model Exam - ko xoa
        model.addAttribute("id", course.getId());

        model.addAttribute("chapter", map);

        return "course/course_resource.html";
    }
}
