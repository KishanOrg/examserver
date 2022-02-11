package com.exam.examserver.controller;


import com.exam.examserver.model.category.Category;
import com.exam.examserver.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    private CrudRepository crudRepositoryj;


    //add Category
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        Category category1 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    //update Category
    @PutMapping("/")
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
        return ResponseEntity.ok(this.categoryService.updateCategory(category));
    }

    //get category
    @GetMapping("/{cid}")
    public Category getCategory(@PathVariable("cid") Long cid) {
        return this.categoryService.getCategory(cid);
    }

    //get all categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories() {

        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    //delete category
    @DeleteMapping("/{cid}")
    public void deleteCategory(@PathVariable Long cid) {
        this.categoryService.deleteCategory(cid);
    }





}


