package com.lms.site.stu_answers;

import com.lms.commom.entity.Question;
import com.lms.commom.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentAnswersRepository extends JpaRepository<StudentAnswer, Integer> {

    @Query("select sa from StudentAnswer sa where sa.studentExam.id =?1")
    public List<StudentAnswer> getListAnswerByExamID(int stu_exam_id);


}
