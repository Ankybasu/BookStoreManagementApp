package com.cg.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.CategoryAlreadyPresentException;
import com.cg.bookstore.exceptions.CategoryNotFoundException;
import com.cg.bookstore.service.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl categoryService;
	
    @PostMapping("/addcategory")
    @ExceptionHandler(CategoryAlreadyPresentException.class)
    ResponseEntity<String> addCategory(@Valid @RequestParam String categoryName) {
        // persisting the category
    	String cat=categoryService.addCategory(categoryName);
        return ResponseEntity.ok("Category "+categoryName+" is valid");
    }
    @GetMapping("/viewallcategories")
    public List<Category> getAllCategories(){
    	return categoryService.viewAllCategories();
    }
	
	@PutMapping("/updatecategory")
	@ExceptionHandler(CategoryNotFoundException.class)
	public Category updateCategory(@RequestBody Category updateCategory) {

		return categoryService.editCategory(updateCategory);
	}
	@DeleteMapping("/deletecategory/{categoryId}")
	@ExceptionHandler(BookNotFoundException.class)
	public String deleteBookingByID(@PathVariable("categoryId") Integer categoryId) {

		return categoryService.removeCategory(categoryId);
	}
	


}
