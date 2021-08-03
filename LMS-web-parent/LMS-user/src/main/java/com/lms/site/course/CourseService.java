/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.commom.entity.Course;

/**
 *
 * @author LENOVO
 */
@Service
@Transactional
public class CourseService {

    private static final int COURSE_PER_PAGE = 4;
    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAllCourse() {
        return (List<Course>) courseRepository.findAll();
    }

    public Page<Course> listByPage(int pageNum, String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, COURSE_PER_PAGE);

        if (keyword != null) {
            return courseRepository.findAll(keyword, pageable);
        }

        return courseRepository.findAll(pageable);
    }

    public Course getCourse(int id) {
        return courseRepository.findById(id).get();
    }

    public List<Integer> getCourseByMemberID(int id){
        return courseRepository.getCourseByMemberID(id);
    }
}
