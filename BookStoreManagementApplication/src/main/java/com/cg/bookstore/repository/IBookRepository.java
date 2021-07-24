package com.cg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Integer>{
	public Book findByTitle(String bookName);
	public List<Book> findByCategory(String category);
	public Optional<Book> findByBookId(int id);

}