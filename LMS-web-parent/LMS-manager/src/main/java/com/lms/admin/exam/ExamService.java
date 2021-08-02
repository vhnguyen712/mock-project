/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.admin.course.CourseRepository;
import com.lms.commom.entity.Exam;

/**
 *
 * @author LENOVO
 */
@Service
@Transactional
public class ExamService {

    private static final int EXAM_PER_PAGE = 4;
    @Autowired
    ExamRepository examRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<Exam> findAll() {
        return (List<Exam>) examRepository.findAll();
    }

    @Autowired
    ExamRepository repository;

    public Page<Exam> listByPage(int id, int pageNum, String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, EXAM_PER_PAGE);

        if (keyword != null) {
            return examRepository.findByCourse_Id(id, pageable, keyword);
        }

        return examRepository.findByCourse_Id(id, pageable);
    }

    public Exam getExam(int id) {
        return examRepository.findById(id).get();
    }

    public void saveExam(Exam exam) {
        examRepository.save(exam);
    }

    public Exam findExamById(int id) {
        return examRepository.findById(id).get();
    }

    public List<Exam> listAllExamOfTeacher(int course_id) {
        List<Exam> listExam = examRepository.listExamsOfTeacher(course_id);
        return listExam;
    }

    public List<Exam> getExamByCourse(int id) {
        return examRepository.findByCourse_Id(id);

    }

    void deleteExam(Integer id) {
        examRepository.deleteById(id);
    }
}
