package com.lms.site.stu_exam;

import com.lms.commom.entity.Exam;
import com.lms.commom.entity.StudentExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentExamService {

    @Autowired
    StudentExamRepository studentExamRepository;

    public StudentExam saveRecord(StudentExam exam){
       return studentExamRepository.save(exam);
    }

    public List<StudentExam> getListStudentExamByEmail(String email){
        return studentExamRepository.getListStudentExamByEmail(email);
    }

    public List<Exam> getListExamByEmail(String email){
        return studentExamRepository.getListExamByEmail(email);
    }

    public List<StudentExam> getListStudentExamByExamId(int id){
        return studentExamRepository.getListStudentExamByExamId(id);
    }

    public void setGrade(int id, float grade){
        studentExamRepository.setGrade(id,grade);
    }
}
