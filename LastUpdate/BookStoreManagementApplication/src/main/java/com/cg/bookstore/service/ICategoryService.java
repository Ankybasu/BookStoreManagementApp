package com.cg.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Category;

@Service
public interface ICategoryService {
	public String addCategory(String categoryName);
	public Category editCategory(Category cat);
	public List<Category> viewAllCategories();
	public String removeCategory(Integer categoryId);
}
