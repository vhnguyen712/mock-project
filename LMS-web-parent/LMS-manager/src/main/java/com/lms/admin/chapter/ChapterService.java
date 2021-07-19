package com.lms.admin.chapter;

import com.lms.admin.course.CourseService;
import com.lms.commom.entity.Course;
import com.lms.commom.entity.Chapter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChapterService {
    
    @Autowired
    ChapterRepository chapterRepository;
    
    @Autowired
    CourseService courseService;
            
    public void saveChapter(Chapter chapter, Course course ){
        chapter.setCourse(course);
        chapterRepository.save(chapter);
    }

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
