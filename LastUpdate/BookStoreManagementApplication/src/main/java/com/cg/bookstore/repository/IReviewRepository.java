package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer>{


	public List<Review> findByBook(int bookId);

	public List<Review> findByCustomer(int customerId);

	public List<Review> findByBook(Book book);

}
