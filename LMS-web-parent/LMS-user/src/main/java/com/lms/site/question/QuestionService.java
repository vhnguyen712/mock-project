package com.lms.site.question;

import com.lms.commom.entity.Answer;
import com.lms.commom.entity.Exam;
import com.lms.commom.entity.Question;
import com.lms.site.exam.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getListQuestion(int id){
        return questionRepository.getListQuestion(id);
    }

    public int findAnswerIdCorrect(int questionId){
        Question question = questionRepository.findById(questionId).get();
        for (Answer answer: question.getAnswers()
             ) {
            if(answer.isCorrect()){
                return answer.getId();
            }
        }return -1;
    }

    public Question getQuestionById(int id){
        return questionRepository.getById(id);
    }
}
