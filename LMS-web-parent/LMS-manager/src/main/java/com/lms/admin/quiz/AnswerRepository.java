/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.quiz;

import com.lms.commom.entity.Answer;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import org.springframework.stereotype.Repository;


@Repository
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Integer> {

    @Query("Select a From Answer  a Where a.question.id = ?1 ")
    List<Answer> listAnswerOfQuestion(int question_id);

    @Query("Select a From Answer a Where a.choice LIKE %?1%")
    public Page<Answer> findAll(String keyword, Pageable pageable);
    
    
}
