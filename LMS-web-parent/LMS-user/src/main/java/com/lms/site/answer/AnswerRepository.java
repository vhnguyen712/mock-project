package com.lms.site.answer;

import com.lms.commom.entity.Answer;
import com.lms.commom.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {


    @Query("select a from Answer a where a.question.id = ?1")
    public List<Answer> getListAnswerByQID(int id);

}