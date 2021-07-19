package com.lms.admin.chapter;

import com.lms.admin.course.CourseService;
import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    
    @Autowired
    ChapterRepository chapterRepository;
    
    @Autowired
    CourseService courseService;
            
    public void saveChapter(Chapter chapter, Course course ){
        chapter.setCourse(course);
        chapterRepository.save(chapter);
    }
}
