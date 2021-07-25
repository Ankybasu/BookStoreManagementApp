package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.CustomerAlreadyPresentException;
import com.cg.bookstore.exceptions.CustomerNotFoundException;
import com.cg.bookstore.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService{


	@Autowired 
	private ICustomerRepository customerServiceRepo;
	
	@Override
	public String createCustomer(Customer c) {
		// TODO Auto-generated method stub
		Optional<Customer> findCustomerById = customerServiceRepo.findById(c.getCustomerId());
		if (!findCustomerById.isPresent()) {
			if(c.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
			customerServiceRepo.save(c);
			return "Created!";
			}
			return "Email invalid";
		}
		else {
			return "Customer cannot be created";
		}
	}

	@Override
	public List<Customer> listCustomers() {
		// TODO Auto-generated method stub
		return customerServiceRepo.findAll();
	}

	@Override
	public String deleteCustomer(Integer id) {
		// TODO Auto-generated method stub
		Optional<Customer> findCustomerById = customerServiceRepo.findById(id);
		if (findCustomerById.isPresent()) {
			customerServiceRepo.deleteById(id);
			return "customer deleted";
		}
		else
		{
			throw new CustomerNotFoundException("Customer does not exist");
		}
	}

	@Override
	public Customer updateCustomer(Customer c) {
		// TODO Auto-generated method stub
		Optional<Customer> findBookById = customerServiceRepo.findById(c.getCustomerId());
		if (findBookById.isPresent()) {
			customerServiceRepo.save(c);
			return c;
		} else
			throw new CustomerNotFoundException(
					"Customer with Id: " + c.getCustomerId() + " not exists!!");
	}

	@Override
	public Customer viewCustomerById(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> findBookById = customerServiceRepo.findById(id);
		if (findBookById.isPresent()) {
			return findBookById.get();
		} else
			throw new CustomerNotFoundException(
					"Customer with Id: " + id+ " not exists!!");
	}

	@Override
	public List<Customer> listAllCustomersByBook(Book book) {
		// TODO Auto-generated method stub
		//return customerServiceRepo.findByBook(book);
		return null;
	}

	@Override
	public Customer viewCustomer(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
