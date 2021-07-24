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
import com.cg.bookstore.exceptions.BookAlreadyPresentException;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.CategoryAlreadyPresentException;
import com.cg.bookstore.service.BookServiceImpl;



@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookServiceImpl bookService;
	
	@PostMapping("/addbook")
    @ExceptionHandler(BookAlreadyPresentException.class)
    ResponseEntity<String> addBook(@Valid @RequestBody Book book) {
        // persisting the book
    	bookService.createBook(book);
        return ResponseEntity.ok("Book "+book.getTitle()+" is valid");	
		
	}
	@GetMapping("/getallbooks")
	public List<Book> getBooks(){
		return bookService.listAllBooks();
	}

	@GetMapping("/showbookbycategory/{category}")
	public List<Book> getBookByCategory(@PathVariable("category")String bookCategory){
		return bookService.listBooksByCategory(bookCategory);
	}
	@GetMapping("/searchbookbyid/{id}")
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<?> searchBookByID(@PathVariable("id") Integer bookId) {

		return bookService.findBookById(bookId);
	}
	
	@PutMapping("/updatebook")
	@ExceptionHandler(BookAlreadyPresentException.class)
	public Book updateBook(@RequestBody Book book){
		return bookService.editBook(book);
	}
	@DeleteMapping("/deletebook/{id}")
	@ExceptionHandler(BookNotFoundException.class)
	public String deleteBookingByID(@PathVariable("id") Integer bookId) {

		return bookService.deleteBook(bookId);
	}
}
