/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.resource;

import com.lms.admin.chapter.ChapterService;
import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.Course;
import com.lms.commom.entity.Resources;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author LENOVO
 */
@Controller
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    FileService fileService;

//    @GetMapping("/add_resource/{id}")
//    public String showAddResourceForm(@PathVariable("id") String id, Model model) {
//        model.addAttribute("resource", new Resources());
//        int chapterId = Integer.parseInt(id);
//        Chapter chapter = chapterService.findChapterById(chapterId);
//        model.addAttribute("chapter", chapter);
//        return "course/resource/add_resource_form";
//    }
    @PostMapping("/upload_file")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {

        try {
            fileService.uploadFile(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
            Resources res = new Resources();
            int chapterId = Integer.parseInt(request.getParameter("chapterId"));
            Chapter chapter = chapterService.findChapterById(chapterId);
            String url = "../../upload/" + file.getOriginalFilename();
            res.setUrl(url);
            resourceService.saveResources(chapter, res);
            List<Chapter> chapterByCourseId = chapterService.getChapterByCourseId(chapter.getCourse().getId());
            model.addAttribute("chapter", chapterByCourseId);
            return "course/course_resource";
        } catch (Exception ex) {
            Logger.getLogger(ResourceController.class.getName()).log(Level.SEVERE, null, ex);
            return "/";
        }
    }
}
