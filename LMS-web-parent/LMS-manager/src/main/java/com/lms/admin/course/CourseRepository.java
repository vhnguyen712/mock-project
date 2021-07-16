/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.course;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lms.commom.entity.Course;

/**
 *
 * @author LENOVO
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>{
    
	@Query("Select c From Course c Where manager_id = ?1 ")
	List<Course> listCoursesOfTeacher(int manager_id);
}
