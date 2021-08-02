package com.lms.site.question;

import com.lms.commom.entity.Exam;
import com.lms.commom.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("select q from Question q where q.exam.id = ?1")
    public List<Question> getListQuestion(int id);

}
