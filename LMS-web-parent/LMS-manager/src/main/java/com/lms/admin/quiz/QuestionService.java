/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.quiz;

import com.lms.commom.entity.Exam;
import com.lms.commom.entity.Question;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
//DAO
@Service
@Transactional
public class QuestionService {

    private static final int QUESTION_PER_PAGE = 4;
    @Autowired
    QuestionRepository questionRepository;

    // list all question
    public List<Question> findAll() {
        try {
            return (List<Question>) questionRepository.findAll();
        } catch (Exception e) {
            System.out.println("ERRORRR");
            e.printStackTrace();
            return (List<Question>) questionRepository.findAll();
        }

    }

    public Page<Question> listByPage(int pageNum, String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, QUESTION_PER_PAGE);

        if (keyword != null) {
            return questionRepository.findAll(keyword, pageable);
        }

        return questionRepository.findAll(pageable);
    }

    // update status
    public void updateEnable(Integer id, boolean enable) {
        questionRepository.enableQuestion(id, enable);
    }

    // save question
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    // delete question
    public void delete(int question_id) {
        questionRepository.deleteById(question_id);
    }

    // question by id
    public Question questionByID(int question_id) {
        return questionRepository.questionByID(question_id);
    }

    // exam by ques
    public Exam examByQuestion(int exam_id) {
        return questionRepository.examByQuestion(exam_id);
    }

}
