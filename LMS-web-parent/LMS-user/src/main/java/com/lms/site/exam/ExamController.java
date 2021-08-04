/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.exam;

import com.lms.commom.entity.*;
import com.lms.site.answer.AnswerService;
import com.lms.site.course.CourseMemberService;
import com.lms.site.course.CourseService;
import com.lms.site.question.QuestionService;
import com.lms.site.sercurity.MyUserDetail;
import com.lms.site.stu_answers.StudentAnswersService;
import com.lms.site.stu_exam.StudentExamService;
import com.lms.site.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Admin
 */
@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    CourseMemberService courseMemberService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @Autowired
    StudentAnswersService studentAnswersService;

    @Autowired
    StudentExamService studentExamService;

    @GetMapping("/my_exams")
    public String showMyExam(Model model, HttpServletRequest request) {

        String email = userService.getEmailOfAuthenticatedUser(request);

        User user = userService.getUserByEmail(email);

        List<CourseMember> listCourseMembers = courseMemberService.getMyCourse(user.getId());

        List<Integer> listCourses = new ArrayList<>();
        for (CourseMember c : listCourseMembers
        ) {
            for (Integer co : courseService.getCourseByMemberID(c.getCourseId())
            ) {
                listCourses.add(co);
            }
        }

        List<Exam> listExam = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = sdf.format(date);
        for (Integer c : listCourses
        ) {
            for (Exam e : examService.getExamByCourseID(c,dateNow)) {
                listExam.add(e);
            }


        }
        if (listExam != null) {
            model.addAttribute("listExam", listExam);
        } else {
            model.addAttribute("Empty", "No exam found.");
        }


        return "my_exams/my_exams";
    }

    @GetMapping("/my_exams/take_exam/{exam_id}")
    public String takeExam(@PathVariable("exam_id") Integer id, Model model) {

        Exam exam = examService.getExam(id);
        int duration = exam.getDuration();

        List<Question> listQuest = questionService.getListQuestion(id);
        if (listQuest != null) {
            model.addAttribute("examId", id);
            model.addAttribute("listQuest", listQuest);
            model.addAttribute("duration", duration);
        } else {
            model.addAttribute("Empty", "No question found.");
        }
        return "my_exams/take_exam";
    }

    @PostMapping("/take_exam")
    public String submitExam( StudentExam studentExam,
                             Model model, HttpServletRequest request){

        int exam_id = Integer.parseInt(request.getParameter("exam_id"));
        Exam exam = examService.getExam(exam_id);
        studentExam.setExam(exam);


        String email = userService.getEmailOfAuthenticatedUser(request);

        User user = userService.getUserByEmail(email);
        studentExam.setUser(user);

        float grade = 0;
        studentExam.setGrade(grade);
        StudentExam newExam = studentExamService.saveRecord(studentExam);


        String[] questionIDs = request.getParameterValues("questionId");

        float count = 0;
        float correctAns = 0;

        for (String questionId: questionIDs
             ) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setExam(exam);
            studentAnswer.setStudentExam(newExam);

            Question question = questionService.getQuestionById(Integer.parseInt(questionId));
            studentAnswer.setQuestion(question);

            Integer answerId = 0;

            String strAnswerId = request.getParameter("question_" + questionId);
            if (strAnswerId==null){
                answerId = null;
                Answer answer = null;
                studentAnswer.setAnswer(answer);
            }else{
                answerId = Integer.parseInt(request.getParameter("question_" + questionId));
                Answer answer = answerService.getAnswerById(answerId);
                studentAnswer.setAnswer(answer);
            }

            int answerIdCorrect = questionService.findAnswerIdCorrect(Integer.parseInt(questionId));
            if(answerId != null){
                if(answerId == answerIdCorrect){
                    studentAnswer.setCorrect(true);
                    correctAns++;
                }else{
                    studentAnswer.setCorrect(false);
                }
            }else{
                studentAnswer.setCorrect(false);
            }
            count ++;
            studentAnswersService.saveRecord(studentAnswer);
        }

        grade = (10/count)*correctAns;

        studentExamService.setGrade(newExam.getId(), grade);

        model.addAttribute("studentExam",studentExam);
        model.addAttribute("count", count);
        model.addAttribute("correct", correctAns);
        return "my_exams/result";
    }

    @GetMapping("/my_exams/my_result")
    public String showMyResult(Model model, HttpServletRequest request) {

        String email = userService.getEmailOfAuthenticatedUser(request);

        List<Exam> listExam = studentExamService.getListExamByEmail(email);

        if (listExam != null) {
            model.addAttribute("listExam", listExam);
        } else {
            model.addAttribute("Empty", "No exam found.");
        }

        return "/my_exams/my_result";
    }

    @GetMapping("/my_exams/my_result/{exam_id}")
    public String showHistory(@PathVariable("exam_id") int id, Model model, HttpServletRequest request) {

        List<StudentExam> listStudentExams = studentExamService.getListStudentExamByExamId(id);

        if (listStudentExams != null) {
            model.addAttribute("listStudentExams", listStudentExams);
        } else {
            model.addAttribute("Empty", "No exam found.");
        }

        return "/my_exams/history";
    }

    @GetMapping("/my_exams/history/{stu_exam_id}")
    public String showDetails(@PathVariable("stu_exam_id") int id, Model model, HttpServletRequest request) {

        List<StudentAnswer> listStudentAnswers = studentAnswersService.getListAnswerByExamID(id);

        if (listStudentAnswers != null) {
            model.addAttribute("listStudentAnswers", listStudentAnswers);
        } else {
            model.addAttribute("Empty", "No exam found.");
        }

        return "/my_exams/details";
    }
}
