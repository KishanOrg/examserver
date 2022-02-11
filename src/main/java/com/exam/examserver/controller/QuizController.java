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
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    //add quiz service
    @PostMapping("/")
    public ResponseEntity<Quiz>  addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //get quiz
    @GetMapping("/")
    public ResponseEntity<?> getQuizzes() {
        return ResponseEntity.ok(this.quizService.getQuizes());
    }

    //get single quiz
    @GetMapping("/{qid}")
    public Quiz getQuiz(@PathVariable("qid") Long qid) {
        return this.quizService.getQuiz(qid);
    }

    //delete quiz
    @DeleteMapping("/{qId}")
    public void  deleteQuiz(@PathVariable("qId") Long qId) {
        this.quizService.deleteQuiz(qId);
    }

    //get all the quiz by category id
    @GetMapping("/category/{cid}")
    public LinkedHashSet<Quiz> getQuizByCategory(@PathVariable("cid") Long cid) {
        return this.quizService.getQuizByCategory(cid);
    }

    //get all the active quizzes
    @GetMapping("/active")
    public  LinkedHashSet<Quiz> getActiveQuizzes() {
        return this.quizService.getActiveQuizzes();
    }


    //get all the active quizzes by category
    @GetMapping("/active/category/{cid}")
    public  LinkedHashSet<Quiz> getActiveQuizzesByCategory(@PathVariable("cid") Long cid) {
        return this.quizService.getActiveQuizzesByCategory(cid);
    }


}
