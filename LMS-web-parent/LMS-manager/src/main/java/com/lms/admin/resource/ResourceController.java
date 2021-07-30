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
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
            String url = file.getOriginalFilename();
            res.setUrl(url);
            resourceService.saveResources(chapter, res);
            
            fileService.copyFile(url);
            
            List<Chapter> chapterByCourseId = chapterService.getChapterByCourseId(chapter.getCourse().getId());
            LinkedHashMap<Chapter, List<Resources>> map = new LinkedHashMap<>();

            for (Chapter chapter2 : chapterByCourseId) {
                System.out.println(chapter2.getName());
                List<Resources> resourceByChapterId = resourceService.getResourceByChapterId(chapter2.getId());

                map.put(chapter2, resourceByChapterId);

            }

            Set<Chapter> keySet = map.keySet();
            for (Chapter key : keySet) {
                System.out.println(key.getName());
            }
            model.addAttribute("chapter", map);
            return "course/course_resource";
        } catch (Exception ex) {
            System.out.println("Lỗi");
            Logger.getLogger(ResourceController.class.getName()).log(Level.SEVERE, null, ex);
            return "/";
        }
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadFile(@Param(value = "url") String url) {
        return new FileSystemResource(new File(url));
    }
}
