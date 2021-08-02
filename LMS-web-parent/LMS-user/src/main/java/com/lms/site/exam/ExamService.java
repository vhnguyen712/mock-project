/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.exam;

import com.lms.commom.entity.Exam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class ExamService {

    
    @Autowired
    ExamRepository examRepository;

    public Exam getExam(int id) {
        return examRepository.findById(id).get();
    }

    public List<Exam> getExamByCourseID(int id, String date){
        return examRepository.getExamByCourseID(id, date);
    }

    public void saveExam(Exam exam) {
        examRepository.save(exam);
    }
}
