/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.quiz;

import com.lms.admin.course.CourseService;
import com.lms.admin.exam.ExamService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.lms.commom.entity.Question;
import com.lms.commom.entity.Answer;
import com.lms.commom.entity.Course;
import com.lms.commom.entity.Exam;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ASUS
 */
@Controller
@Transactional
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    ExamService examService;

    @Autowired
    CourseService courseService;
    
    @Autowired
    AnswerService answerService;

    @PersistenceContext
    EntityManager entityManager;

    // load page view quiz
    @GetMapping("/view_quiz")
    public String listFirstPage(Model model) {

        return listByPage(1, model, null);
    }

    // load footer     
    @GetMapping("/view_quiz/page/{pageNum}")
    public String listByPage(@PathVariable("pageNum") int pageNum, Model model, @Param("keyword") String keyword) {
        Page<Question> page = questionService.listByPage(pageNum, keyword);
        List<Question> listQuestions = page.getContent();

        if (listQuestions != null) {
            model.addAttribute("listQuestions", listQuestions);
        } else {
            model.addAttribute("Empty", "No course found.");
        }

        long totalItem = page.getTotalElements();
        int totalPage = page.getTotalPages();

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("listQuestions", listQuestions);
        model.addAttribute("keyword", keyword);

        return "quiz/view_quiz";
    }

    // update active question
    @GetMapping("/view_quiz/{id}/enable/{status}")
    public String updateEnable(@PathVariable("id") Integer id, @PathVariable("status") boolean enable, RedirectAttributes redirectAttributes) {

        questionService.updateEnable(id, enable);
        String msg = enable ? "Enabled" : "Disable";

        redirectAttributes.addFlashAttribute("message", msg);

        return "redirect:/view_quiz";
    }

    // load page create question    
    @GetMapping("/create_question")
    public String showCreateQuestion(Model model) {
        try {
            List<Exam> listExam = examService.findAll();
            List<Course> listCourse = courseService.findAllCourse();
            model.addAttribute("exams", listExam);
            model.addAttribute("courses", listCourse);
            model.addAttribute("question", new Question());
//            System.out.println(listCourse);
            return "quiz/create_question";
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return "quiz/create_question";
        }

    }

    // create question
    @PostMapping("/create_question")
    public String createQuestion(Model model, @ModelAttribute("question") Question question,
            HttpServletRequest request, RedirectAttributes redirectAttributes) {

        // exam 
        String examID = request.getParameter("optExam");
        System.out.println(examID);

        //answer
        String answer1 = request.getParameter("answer1");
        String answer2 = request.getParameter("answer2");
        String answer3 = request.getParameter("answer3");
        String answer4 = request.getParameter("answer4");

        // answer correct
        String correct = request.getParameter("correct");

        // question : question , status
        String txtQuestion = request.getParameter("txtquestion");
        boolean active = Boolean.valueOf(request.getParameter("active"));

        // submit
        String submit = request.getParameter("submit");
        System.out.println(submit);

        // get question id (update)
        String questionID = null;
        try {
            questionID = request.getParameter("questionID");
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

        // get answer id (update)
        String answer1ID = null;
        String answer2ID = null;
        String answer3ID = null;
        String answer4ID = null;
        try {
            answer1ID = request.getParameter("answer1ID");
            answer2ID = request.getParameter("answer2ID");
            answer3ID = request.getParameter("answer3ID");
            answer4ID = request.getParameter("answer4ID");
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

        // check exam
        if (!examID.equals("")) {
            try {
                // get exam
                Exam exam = examService.findExamById(Integer.valueOf(examID.trim()));

                // set answer correct
                boolean correct1 = false, correct2 = false, correct3 = false, correct4 = false;
                if (Integer.valueOf(correct) == 1) {
                    correct1 = true;
                } else if (Integer.valueOf(correct) == 2) {
                    correct2 = true;
                } else if (Integer.valueOf(correct) == 3) {
                    correct3 = true;
                } else if (Integer.valueOf(correct) == 4) {
                    correct4 = true;
                }

                Answer answers1 = new Answer(answer1, correct1, exam);

                Answer answers2 = new Answer(answer2, correct2, exam);

                Answer answers3 = new Answer(answer3, correct3, exam);

                Answer answers4 = new Answer(answer4, correct4, exam);

                // Answer  set question_id
                // answer 1
                answers1.setQuestion(question);
                if(answer1ID != null){
                    answers1.setId(Integer.valueOf(answer1ID));
                }
                
                // answer 2
                answers2.setQuestion(question);
                if(answer2ID != null){
                    answers2.setId(Integer.valueOf(answer2ID));
                }
                
                // answer 3
                answers3.setQuestion(question);
                if(answer3ID != null){
                    answers3.setId(Integer.valueOf(answer3ID));
                }
                
                //answer 4
                answers4.setQuestion(question);
                if(answer4ID != null){
                    answers4.setId(Integer.valueOf(answer4ID));
                }

                // question set question , exam
                question.setQuestion(txtQuestion);
                question.setExam(exam);
                question.setStatus(active);
                if (questionID != null) {
                    question.setId(Integer.valueOf(questionID.trim()));
                }

                // Question  set answer_id
                question.addAnswer(answers1);
                question.addAnswer(answers2);
                question.addAnswer(answers3);
                question.addAnswer(answers4);

                // luu vao database ???
//                entityManager.persist(question);
                questionService.saveQuestion(question);

                // tải lại list exam để tạo tiếp
                List<Exam> listExam = examService.findAll();
                model.addAttribute("exams", listExam);

                // thông báo thành công
                if (submit.equals("Create Quiz")) {
                    redirectAttributes.addFlashAttribute("message", "Create Success");
                    return "redirect:/view_quiz";
                }
                if (submit.equals("Save")) {
                    redirectAttributes.addFlashAttribute("message", "Update Success");
                    return "redirect:/view_quiz";
                } else {
                    return "redirect:/view_quiz";
                }

            } catch (Exception e) {
                System.out.println("create fail");
                e.printStackTrace();

                // tải lại list exam để tạo tiếp
                List<Exam> listExam = examService.findAll();
                model.addAttribute("exams", listExam);

                // thông báo
                redirectAttributes.addFlashAttribute("message", "Fail !!!");
                return "redirect:/create_question";
            }

        } else {
            System.out.println("create fail");
            // tải lại list exam để tạo tiếp
            List<Exam> listExam = examService.findAll();
            model.addAttribute("exams", listExam);

            // thông báo
            redirectAttributes.addFlashAttribute("message", "Please Choose Exam");
            return "redirect:/create_question";
        }
    }

    
    // delete question
    @GetMapping("/view_quiz/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Model model) {
        try {
            questionService.delete(id);
            redirectAttributes.addFlashAttribute("message", "The question have been deleted");

        } catch (UsernameNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/view_quiz";
    }

    // view edit page
    @GetMapping("/view_quiz/edit/{id}")
    public String viewAnswer(@PathVariable("id") Integer id, Model model) {
        try {
            List<Course> listCourse = courseService.findAllCourse();
            Question question = questionService.questionByID(id);
            List<Answer> answers = answerService.getAnswerByQuestion(id);
            List<Exam> listExam = examService.findAll();
            Answer answer1 = answers.get(0), answer2 = answers.get(1),
                    answer3 = answers.get(2), answer4 = answers.get(3);

            // set question status
            boolean yes = false, no = false;
            if (question.isStatus()) {
                yes = true;
            } else {
                no = true;
            }

            // add attribute
            model.addAttribute("exams", listExam);
            model.addAttribute("courses", listCourse);
            model.addAttribute("question", question);
            model.addAttribute("answer1", answer1);
            model.addAttribute("answer2", answer2);
            model.addAttribute("answer3", answer3);
            model.addAttribute("answer4", answer4);
            model.addAttribute("yes", yes);
            model.addAttribute("no", no);
            return "quiz/view_answer";
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return "quiz/view_answer";
        }
    }

}
