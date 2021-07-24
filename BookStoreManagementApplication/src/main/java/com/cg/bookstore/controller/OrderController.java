package com.cg.bookstore.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.OOrder;
import com.cg.bookstore.exceptions.OrderNotPlacedException;
import com.cg.bookstore.service.OrderServiceImpll;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderServiceImpll orderService;
	
	@GetMapping("/vieworders")
	    List<OOrder> listAllOrders(){
	        return orderService.listAllOrders();
		}

	
	@PostMapping("/addtocart")
    @ExceptionHandler(OrderNotPlacedException.class)
    ResponseEntity<String> addOrder(@Valid @RequestBody OOrder od){
        // persisting the order
		orderService.addOrder(od);
        return ResponseEntity.ok("Order added to cart!");	
	}
	@PostMapping("/checkout")
    @ExceptionHandler(OrderNotPlacedException.class)
    BookOrder placeOrder(@RequestBody BookOrder bookOrder){
		return orderService.calculateTotal(bookOrder);
        //return ResponseEntity.ok("Order placed!");	
	
}
}