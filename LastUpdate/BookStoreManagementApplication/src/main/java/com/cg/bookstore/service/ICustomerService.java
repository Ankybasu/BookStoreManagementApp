package com.cg.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.exceptions.CustomerAlreadyPresentException;

@Service
public interface ICustomerService {

	public String createCustomer(Customer c) throws CustomerAlreadyPresentException;
	public List<Customer> listCustomers();
	//public Customer deleteCustomer(Customer c);
	public Customer updateCustomer(Customer c);
	public Customer viewCustomer(Customer c);
	public List<Customer> listAllCustomersByBook(Book book);
	public String deleteCustomer(Integer id);
	//public Customer viewCustomer(int id);
	public Customer viewCustomerById(int id);

	
}