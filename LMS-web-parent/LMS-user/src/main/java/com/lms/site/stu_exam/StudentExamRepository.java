package com.lms.site.stu_exam;

import com.lms.commom.entity.Question;
import com.lms.commom.entity.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentExamRepository extends JpaRepository<StudentExam, Integer> {

    @Query("select se from StudentExam se where se.user.email like ?1")
    public List<StudentExam> getListStudentExamByEmail(String email);

    @Query("update StudentExam se set se.grade = ?2 where se.id = ?1")
    @Modifying
    public void setGrade(int id, float grade);
}
