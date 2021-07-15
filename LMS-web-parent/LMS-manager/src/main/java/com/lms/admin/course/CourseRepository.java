/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.course;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lms.commom.entity.Course;

/**
 *
 * @author LENOVO
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>{
    
}
