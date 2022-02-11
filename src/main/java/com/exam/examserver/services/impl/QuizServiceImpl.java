package com.exam.examserver.services.impl;

import com.exam.examserver.model.category.Category;
import com.exam.examserver.model.category.Quiz;
import com.exam.examserver.repo.QuizRepository;
import com.exam.examserver.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public Set<Quiz> getQuizes() {
        return new LinkedHashSet<>(this.quizRepository.findAll());
    }

    @Override
    public void deleteQuiz(Long quizId) {
        this.quizRepository.deleteById(quizId);
    }

    @Override
    public LinkedHashSet<Quiz> getQuizByCategory(Long cid) {
        Category category = new Category();
        category.setCid(cid);
        return new LinkedHashSet<Quiz>(this.quizRepository.findByCategory(category));
    }

    //get all the active quizzes
    @Override
    public LinkedHashSet<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByActive(true);
    }

    //get all the active quizzes by category
    @Override
    public LinkedHashSet<Quiz> getActiveQuizzesByCategory(Long cid) {
        Category category = new Category();
        category.setCid(cid);
        return this.quizRepository.findByActiveAndCategory(true, category);
    }
}
