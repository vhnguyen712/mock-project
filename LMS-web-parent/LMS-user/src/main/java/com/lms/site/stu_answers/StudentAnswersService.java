package com.lms.site.stu_answers;

import com.lms.commom.entity.StudentAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentAnswersService {

    @Autowired
    StudentAnswersRepository studentAnswersRepository;

    public void saveRecord(StudentAnswer answer){

        studentAnswersRepository.save(answer);
    }

    public List<StudentAnswer> getListAnswerByExamID(int stu_exam_Id){
        return studentAnswersRepository.getListAnswerByExamID(stu_exam_Id);
    }
}
