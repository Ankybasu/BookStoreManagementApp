package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exceptions.CategoryAlreadyPresentException;
import com.cg.bookstore.exceptions.CategoryNotFoundException;
import com.cg.bookstore.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private ICategoryRepository categoryServiceRepo;
	

	@Override
	public String addCategory(String categoryName) {
		// TODO Auto-generated method stub
		Optional<Category> findCategoryByCategoryName = categoryServiceRepo.findByCategoryName(categoryName);
		if (!findCategoryByCategoryName.isPresent()) {
			 categoryServiceRepo.save(new Category(categoryName));
			 return "Category added!";
		} else
			throw new CategoryAlreadyPresentException(
					"Category " + categoryName + " already exists!!");
	}

	
	@Override
	public Category editCategory(Category cat) {
		// TODO Auto-generated method stub
		Optional<Category> findCategoryByCategoryName = categoryServiceRepo.findById(cat.getCategoryId());
		if (findCategoryByCategoryName.isPresent()) {
			categoryServiceRepo.save(cat);
			return cat;
		} else
			throw new CategoryNotFoundException(
					"Category with Id: " + cat.getCategoryId() + " not exists!!");
	}

	@Override
	public String removeCategory(Integer categoryId){
		// TODO Auto-generated method stub
		Optional<Category> findBookingByCategoryId =categoryServiceRepo.findById(categoryId);
		if (findBookingByCategoryId.isPresent()) {
			categoryServiceRepo.deleteById(categoryId);
			return "Category Deleted!!";
		} else
			throw new CategoryNotFoundException("Category not found for the entered categoryName");
	}
	

	@Override
	public List<Category> viewAllCategories() {
		// TODO Auto-generated method stub
		return categoryServiceRepo.findAll();
	}

}
