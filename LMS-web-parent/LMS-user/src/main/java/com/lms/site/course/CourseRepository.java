/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lms.commom.entity.Course;

/**
 *
 * @author LENOVO
 */
@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer>{
 
	
	@Query("Select c From Course c Where c.name LIKE %?1%")
	public Page<Course> findAll(String keyword, Pageable pageable);
}
