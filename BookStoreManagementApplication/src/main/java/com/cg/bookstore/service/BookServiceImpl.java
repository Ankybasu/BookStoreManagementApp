package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exceptions.BookAlreadyPresentException;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.CategoryAlreadyPresentException;
import com.cg.bookstore.repository.IBookRepository;

@Service
public class BookServiceImpl implements IBookService{
	@Autowired 
	private IBookRepository bookServiceRepo;
	
	@Override
	public Book createBook(Book book) {
		Optional<Book> findBookById = bookServiceRepo.findById(book.getBookId());
		if (!findBookById.isPresent()) {
			return bookServiceRepo.save(book);
		} else
			throw new BookAlreadyPresentException(
					"Book " + book.getTitle() + " already exists!!");
	}
	@Override
	public List<Book> listAllBooks() {
		// TODO Auto-generated method stub
		return bookServiceRepo.findAll();
	}


	@Override
	public Book editBook(Book book) {
		// TODO Auto-generated method stub
		Optional<Book> findBookById = bookServiceRepo.findById(book.getBookId());
		if (findBookById.isPresent()) {
			bookServiceRepo.save(book);
			return book;
		} else
			throw new BookNotFoundException(
					"Book with Id: " + book.getBookId() + " not exists!!");
	}


	@Override
	public List<Book> listBooksByCategory(String cat) {
		// TODO Auto-generated method stub
		return bookServiceRepo.findByCategory(cat);
	}

	@Override
	public Book viewBookByName(String bookName) {
		// TODO Auto-generated method stub
		return bookServiceRepo.findByTitle(bookName);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResponseEntity<?> findBookById(Integer bid) {
		// TODO Auto-generated method stub
			Optional<Book> findById = bookServiceRepo.findById(bid);
			try {
				if (findById.isPresent()) {
					Book findBook = findById.get();
					return new ResponseEntity<Book>(findBook, HttpStatus.OK);
				} else
					throw new BookNotFoundException("No record found with ID " + bid);
			} catch (BookNotFoundException e) {
				return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		}

	@Override
	public String deleteBook(Integer bookId) {
		Optional<Book> findBookById = bookServiceRepo.findById(bookId);
		if (findBookById.isPresent()) {
			bookServiceRepo.deleteById(bookId);
			return "Book Deleted!!";
		} else
			throw new BookNotFoundException("Book not found for the entered BookID");
	}
}




