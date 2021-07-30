/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.chapter;

import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.CourseMember;
import com.lms.commom.entity.Resources;
import com.lms.site.course.CourseService;
import com.lms.site.resource.ResourceService;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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

    @Autowired
    ResourceService resourceService;

    @GetMapping("/join")
    public String showChapter(@ModelAttribute("member") CourseMember member, Model model) {

        List<Chapter> chapterByCourseId = chapterService.getChapterByCourseId(member.getCourseId());

        //
        LinkedHashMap<Chapter, List<Resources>> map = new LinkedHashMap<Chapter, List<Resources>>();

        for (Chapter chapter : chapterByCourseId) {
            System.out.println(chapter.getName());
            List<Resources> resourceByChapterId = resourceService.getResourceByChapterId(chapter.getId());

            map.put(chapter, resourceByChapterId);

        }

        Set<Chapter> keySet = map.keySet();
        for (Chapter key : keySet) {
            System.out.println(key.getName());
        }
        model.addAttribute("chapter", map);
        //

        return "course/course_resource.html";
    }
}
