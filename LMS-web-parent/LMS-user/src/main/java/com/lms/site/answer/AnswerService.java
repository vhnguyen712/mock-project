package com.lms.site.answer;

import com.lms.commom.entity.Answer;
import com.lms.commom.entity.Question;
import com.lms.site.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> getListAnswerByQID(int id){
        return answerRepository.getListAnswerByQID(id);
    }

    public Answer getAnswerById(int id){
        return answerRepository.getById(id);
    }
}
