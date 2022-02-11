package com.exam.examserver.repo;

import com.exam.examserver.model.category.Question;
import com.exam.examserver.model.category.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    public Set<Question> findByQuiz(Quiz quiz);
}
