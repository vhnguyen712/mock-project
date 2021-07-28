/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.quiz;

import com.lms.commom.entity.Exam;
import com.lms.commom.entity.Question;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
//DATABASE
@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer> {

    @Query("Select q From Question  q Where q.exam.id = ?1 ")
    List<Question> listQuestionOfExam(int exam_id);

    @Query("Select q From Question q Where q.question LIKE %?1%")
    public Page<Question> findAll(String keyword, Pageable pageable);

    @Query("Update Question m Set m.status = ?2 Where m.id = ?1")
    @Modifying
    public void enableQuestion(Integer id, boolean enable);

    @Query("Select q From Question q Where q.id = ?1 ")
    public Question questionByID(int question_id);
    
    @Query("Select e From Exam e where e.id = ?1")
    public Exam examByQuestion(int exam_id);
}
