package com.cg.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.service.CustomerServiceImpl;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private CustomerServiceImpl customerService;
	
	@PostMapping("/addcustomer")
	public String addCust(@RequestBody Customer c) {
		return customerService.createCustomer(c);
	}
	@GetMapping("/viewdetails/{id}")
	public Customer viewDetails(@PathVariable("id") int id) {
		return customerService.viewCustomerById(id);
	}
	
}
