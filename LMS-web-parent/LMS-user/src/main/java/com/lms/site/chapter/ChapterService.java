/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.chapter;

import com.lms.commom.entity.Chapter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class ChapterService {
    
    @Autowired
    ChapterRepository repository;
    
    
    Chapter getChapter() {
        return repository.findById(1).get();
    }
    
    List<Chapter> getChapterByCourseIdCheat(int courseId) {
        List<Chapter> findAll = repository.findAll();
        List<Chapter> found = new ArrayList<>();
        for (Chapter chapter : findAll) {
            if (chapter.getCourse().getId() == courseId) {
                found.add(chapter);
            }
        }
        return found;
    }
    
    List<Chapter> getChapterByCourseId(int id) {
        return repository.findByCourse_Id(id);
    }
}
