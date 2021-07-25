package com.cg.bookstore.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="booktable")
public class Book {
	@Id
	@SequenceGenerator(name="bookSeqGen",sequenceName="bookSeq",initialValue=101,allocationSize=100)
	@GeneratedValue(generator="bookSeqGen")
	@Column(name="book_id")
	private int bookId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="description")
	private String description;
	
	@Column(name="isbn")
	private String isbn;
	
	@Column(name="price")
	private double price;
	
	@Column(name="publishdate")
	private LocalDate publishDate;
	
	@Column(name="lastupdatedon")
	private LocalDate lastUpdatedOn;
	
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();
	


	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Book() {
		super();
	}

	public Book(String title, String author, Category category, String description, String isbn, double price,
			LocalDate publishDate, LocalDate lastUpdatedOn) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.description = description;
		this.isbn = isbn;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public LocalDate getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

}
