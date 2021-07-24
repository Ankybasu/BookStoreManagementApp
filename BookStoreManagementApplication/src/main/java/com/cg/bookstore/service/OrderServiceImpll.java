package com.cg.bookstore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OOrder;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.repository.IBookOrderRepository;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.IOrderRepositorys;

@Service
public class OrderServiceImpll implements IOrderServices{

	@Autowired
	private IOrderRepositorys orderRepo;
	@Autowired
	private ICustomerRepository customerRepo;
	@Autowired
	private IBookRepository bookRepo;
	@Autowired
	private IBookOrderRepository bookOrderRepo;
	
	@Override
	public List<OOrder> listAllOrders() {
		// TODO Auto-generated method stub
		return orderRepo.findAll();
	}
	@Override
	public List<OOrder> listOrderByCustomer(Customer cs) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OOrder viewOrderForCustomer(OOrder od) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OOrder viewOrderForAdmin(OOrder od) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OOrder cancelOrder(OOrder od) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OOrder addOrder(OOrder od) {
		// TODO Auto-generated method stub
		int id=od.getBook().getBookId();
		Optional<Book> b=bookRepo.findByBookId(id);
		if(b.isPresent()) {
			double price=b.get().getPrice();
			double subtotal=price*od.getQuantity();
			Book temp=bookRepo.getById(id);
			//od.setBook(b.get());
			//od.setSubtotal(subtotal);
			return orderRepo.save(new OOrder(temp,od.getQuantity(), subtotal));
		}
		else 
			throw new BookNotFoundException("Book not found!!");
	}
	@Override
	public OOrder updateOrder(OOrder od) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<OOrder> viewOrderByBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Book> listBestSellingBook() {
		// TODO Auto-generated method stub
		return null;
	}


	public BookOrder calculateTotal(BookOrder bookOrder) {
		//Address ad=customerRepo.findById(bookOrder.getCustomer().getCustomerId()).get().getAddress();
		Customer ad=customerRepo.findById(bookOrder.getCustomer().getCustomerId()).get();
		double orderTotal=0;
		List<OOrder> orderList=orderRepo.findAll();
		for(OOrder i: orderList) {
			orderTotal+=i.getSubtotal();
		}
		System.out.println(orderTotal+""+ad);
		return bookOrderRepo.save(new BookOrder(ad,bookOrder.getOrderDate(),orderTotal,"placed",
				ad.getAddress(),bookOrder.getPaymentMethod(),bookOrder.getRecipientName(),bookOrder.getRecipientPhone()));
		
	}


}