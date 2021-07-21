/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.exam;

import com.lms.admin.exam.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lms.commom.entity.Course;
import com.lms.commom.entity.Exam;
import java.util.Date;

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

    public List<Exam> findAll() {
        return (List<Exam>) examRepository.findAll();
    }

    public Page<Exam> listByPage(int pageNum, String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, EXAM_PER_PAGE);

        if (keyword != null) {
            return examRepository.findAll(keyword, pageable);
        }

        return examRepository.findAll(pageable);
    }

    public Exam getExam(int id) {
        return examRepository.findById(id).get();
    }

    public void saveExam(Exam exam) {
        //   exam.setAvailable(new Date());
        exam.setCreateDate(new Date());
        //   exam.setCourse();
        examRepository.save(exam);
    }

    public Exam findExamById(int id) {
        return examRepository.findById(id).get();
    }
}
