package com.exam.examserver.services;

import com.exam.examserver.model.category.Category;
import com.exam.examserver.model.category.Quiz;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Quiz getQuiz(Long quizId);

    public Set<Quiz> getQuizes();

    public void deleteQuiz(Long quizId);

    public LinkedHashSet<Quiz> getQuizByCategory(Long cid);

    //get active quizzes
    public LinkedHashSet<Quiz> getActiveQuizzes();

    //get acteve quizzes by category
    public LinkedHashSet<Quiz> getActiveQuizzesByCategory(Long cid);
}
