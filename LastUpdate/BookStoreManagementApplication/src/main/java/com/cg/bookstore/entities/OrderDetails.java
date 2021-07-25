package com.cg.bookstore.entities;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true) 
@Table(name="orderdetailstable")
public class OrderDetails{
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="odrSeqGen",sequenceName="odrSeq",initialValue=501,allocationSize=100)
	@GeneratedValue(generator="odrSeqGen")
	@Column(name="orderdetails_id",nullable = false)
	private int orderDetailsId;
	
	
	@OneToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	
	@ManyToOne
	@JoinColumn(name="bookorder_id")
	private BookOrder bookOrder;

	public OrderDetails(Book book,int quantity, double subtotal) {
		super();
		this.book = book;
		//this.bookOrder = bookOrder;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="subtotal")
	private double subtotal;

	public OrderDetails() {
		super();
	}
	
	public int getOrderDetailsId() {
		return orderDetailsId;
	}


	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}


	public BookOrder getBookOrder() {
		return bookOrder;
	}


	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}


	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

	 
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

}