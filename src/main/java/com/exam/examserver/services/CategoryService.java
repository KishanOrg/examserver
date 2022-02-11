package com.exam.examserver.services;

import com.exam.examserver.model.category.Category;

import java.util.Set;

public interface CategoryService {

    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public Category getCategory(Long categoryId);

    public Set<Category> getCategories();

    public void deleteCategory(Long cid);

}
