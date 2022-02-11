package com.exam.examserver.services;

import com.exam.examserver.model.category.Category;
import com.exam.examserver.model.category.Question;
import com.exam.examserver.model.category.Quiz;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Question getQuestion(Long questionId);

    public Set<Question> getQuestions();

    public Set<Question> getQuestionByQuiz(Quiz quiz);

    public void deleteQuestion(Long questionId);


}
