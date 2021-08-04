/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.admin.sercurity.MyManagerDetail;
import com.lms.commom.entity.Course;
import com.lms.commom.entity.Manager;

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

	public void saveCourse(Course course, Authentication authentication) {
		MyManagerDetail managerDetail = (MyManagerDetail) authentication.getPrincipal();

		Manager manager = managerDetail.getManager();
		course.setManager(manager);
		courseRepository.save(course);
	}

	public List<Course> listAllCourseOfTeacher(int manager_id) {

		List<Course> listCourse = courseRepository.listCoursesOfTeacher(manager_id);

		return listCourse;
	}

	public Course findCourseById(int id) {
		return courseRepository.findById(id).get();
	}
	
	public Course findCourseByName(String name) {
		return courseRepository.findByName(name);
	}

	public void delete(Integer id) {
            courseRepository.deleteById(id);
	}
}
