/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    CourseRepository courseRepository;
    
    public List<Course> findAllCourse(){
        return (List<Course>) courseRepository.findAll(); 
    }
            
    public Course getCourse(int id){
        return courseRepository.findById(id).get();
    }
    
}
