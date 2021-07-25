package com.cg.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.repository.IBookOrderRepository;

@Service
public class BookOrderServiceImpl {
	@Autowired
	private IBookOrderRepository bookOrderRepo;
	/*
	 * list all orders of the customers
	 */
	public List<BookOrder> viewAllOrders() {
		// TODO Auto-generated method stub
		return bookOrderRepo.findAll();
	}
	/*
	 * 
	 */

}
