/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.quiz;

import com.lms.commom.entity.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AnswerService {

    private static final int ANSWER_PER_PAGE = 4;
    @Autowired
    AnswerRepository answerRepository;

    // list answer
    public List<Answer> findAll() {
        return (List<Answer>) answerRepository.findAll();
    }
    
    // list answer * 4 by question 
    public List<Answer> getAnswerByQuestion(int question_id){
        return answerRepository.listAnswerOfQuestion(question_id);
    }
    
    
}
