package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.exceptions.ReviewNotFoundException;
import com.cg.bookstore.repository.IReviewRepository;


@Service
public class ReviewServiceImpl implements IReviewService{
	@Autowired 
	private IReviewRepository reviewServiceRepo;
	
	@Override
	public List<Review> listAllReviews() {
		// TODO Auto-generated method stub
		return reviewServiceRepo.findAll();
	}

	@Override
	public String deleteReview(Review review) {
		// TODO Auto-generated method stub
		Optional<Review> findReviewById = reviewServiceRepo.findById(review.getReviewId());
		if (findReviewById.isPresent()) {
			reviewServiceRepo.deleteById(review.getReviewId());
			return "Review Deleted!!";
		} else
			throw new ReviewNotFoundException("Review not found for the entered ReviewID");
	}
	@Override
	public Review updateReview(Review review) {
		// TODO Auto-generated method stub
		Optional<Review> findReviewById = reviewServiceRepo.findById(review.getReviewId());
		if (findReviewById.isPresent()) {
			reviewServiceRepo.save(review);
			return review;
		} else
			throw new ReviewNotFoundException(
					"Review with Id: " + review.getReviewId() + " not exists!!");
	}
	@Override
	public Review viewReview(Integer reviewId) {
		// TODO Auto-generated method stub
		Optional<Review> findReviewById = reviewServiceRepo.findById(reviewId);
		if (findReviewById.isPresent()) {
			return findReviewById.get();
		} else
			throw new ReviewNotFoundException("Review not found for the entered ReviewID");
		}
	@Override
	public List<Review> listAllReviewsByCustomer(int custId) {
		// TODO Auto-generated method stub
		return reviewServiceRepo.findByCustomer(custId);
	}

	@Override
	public Review addReview(Review review) {
		// TODO Auto-generated method stub
		return reviewServiceRepo.save(review);
	}

	@Override
	public List<Book> listMostFavoredBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> listAllReviewsByBook(Book book) {
		// TODO Auto-generated method stub
		return reviewServiceRepo.findByBook(book);
	}

}