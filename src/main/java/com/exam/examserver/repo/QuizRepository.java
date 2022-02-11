package com.exam.examserver.repo;

import com.exam.examserver.model.category.Category;
import com.exam.examserver.model.category.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedHashSet;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public LinkedHashSet<Quiz> findByCategory(Category category);

    //get active quizzes
    public LinkedHashSet<Quiz> findByActive(Boolean b);

    //get active quizzes by category
    public LinkedHashSet<Quiz> findByActiveAndCategory(Boolean b, Category c);
}
