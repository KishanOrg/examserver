package com.exam.examserver.controller;


import com.exam.examserver.model.category.Question;
import com.exam.examserver.model.category.Quiz;
import com.exam.examserver.services.QuestionService;
import com.exam.examserver.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //update question
    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //get max number of  question of any quiz --> for user
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid) {
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestion();
        List<Question> list = new ArrayList<Question>(questions);

        if(list.size() > Integer.parseInt(quiz.getNumOfQsn())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumOfQsn()));
        }

        for (Question q : list) {
            q.setAnswer("");
        }
        Collections.shuffle(list);

        return ResponseEntity.ok(list);
    }

    //get all question of any quiz --> for admin
    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable("qid") Long qid) {
        Quiz quiz = new Quiz();
        quiz.setqId(qid);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionByQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);
    }


    //get single question
    @GetMapping("/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId) {
        return this.questionService.getQuestion(quesId);
    }

    //delete question
    @DeleteMapping("/{quesId}")
    public void deletetQuestion(@PathVariable("quesId") Long quesId) {
        this.questionService.deleteQuestion(quesId);
    }


    // evaluating the quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        double marksGot ;
        int correctAnswers = 0 ;
        int attempted = 0;
        for(Question q : questions){
            Question question = this.questionService.getQuestion(q.getQuesId());
            if(question.getAnswer().equals(q.getGivenAnswer())) {
                correctAnswers ++;
            }
            if(q.getGivenAnswer() != null){
                attempted++;
            }
        }
        double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
        marksGot = correctAnswers * marksSingle;

        Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
        return ResponseEntity.ok(map);
    }






}
