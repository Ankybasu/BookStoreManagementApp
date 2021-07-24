package com.cg.bookstore.controller;

import java.util.List;
import java.util.Optional;

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

import com.cg.bookstore.entities.Review;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exceptions.BookAlreadyPresentException;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.CategoryAlreadyPresentException;
import com.cg.bookstore.exceptions.CategoryNotFoundException;
import com.cg.bookstore.exceptions.ReviewAlreadyPresentException;
import com.cg.bookstore.exceptions.ReviewNotFoundException;
import com.cg.bookstore.service.ReviewServiceImpl;

@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewServiceImpl reviewService;
	
    @PostMapping("/addreview")
    ResponseEntity<String> addReview(@Valid @RequestParam Review review) {
        // persisting the category
    	Review r= reviewService.addReview(review);
        return ResponseEntity.ok("Review "+ review +" is valid");
    }
    @GetMapping("/listallreview")
    public List<Review> getAllReview(){
    	return reviewService.listAllReviews();
    }
	
	@PutMapping("/updatereview")
	@ExceptionHandler(ReviewAlreadyPresentException.class)
	public Review updateReview(@RequestBody Review review){
		return reviewService.updateReview(review);
	}
	
	@DeleteMapping("/deletereview")
	@ExceptionHandler(ReviewNotFoundException.class)
	public String deleteReviewByReviewID(@PathVariable("reviewId") Review review) {

		return reviewService.deleteReview(review);
	}
	 @GetMapping("/viewreview")
	 @ExceptionHandler(ReviewNotFoundException.class)
	    public Review viewReviewByReviewId(@PathVariable("reviewId") int reviewId) {
	    	return reviewService.viewReview(reviewId);
	    }
	 @GetMapping("/listallreviewsbybook/{bookName}")
	    public List<Review> getAllReviewByBook(@RequestBody Book book){
			return reviewService.listAllReviewsByBook(book);
	    }
	 @GetMapping("/listallreviewsbycustomer/{custid}")
	    public List<Review> getAllReviewByCustomer(@PathVariable("custid") int custId){
	    	return reviewService.listAllReviewsByCustomer(custId);
	    }

}
