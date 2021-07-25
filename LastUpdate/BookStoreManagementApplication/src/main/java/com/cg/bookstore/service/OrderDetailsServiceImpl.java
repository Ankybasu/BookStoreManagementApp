package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OOrder;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.repository.IBookOrderRepository;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.IOrderDetailsRepository;
import com.cg.bookstore.repository.IOrderRepositorys;

@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService{

	@Autowired
	private IOrderDetailsRepository orderRepo;
	@Autowired
	//private IMainOrderRepository mainOrder;
	private IOrderRepositorys mainOrder;
	@Autowired
	private ICustomerRepository customerRepo;
	@Autowired
	private IBookRepository bookRepo;
	@Autowired
	private IBookOrderRepository bookOrderRepo;
	
	/*
	 * views order for admin
	 */
	@Override
	public List<BookOrder> listAllOrders() {
		// TODO Auto-generated method stub
		return bookOrderRepo.findAll();
	}

	@Override
	public List<OrderDetails> listOrderByCustomer(Customer cs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails viewOrderForCustomer(OrderDetails od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails viewOrderForAdmin(OrderDetails od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails cancelOrder(OrderDetails od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails addOrder(OrderDetails od) {
		// TODO Auto-generated method stub
		int id=od.getBook().getBookId();
		Optional<Book> b=bookRepo.findByBookId(id);
		if(b.isPresent()) {
			double price=b.get().getPrice();
			double subtotal=price*od.getQuantity();
			Book temp=bookRepo.getById(id);
			//od.setBook(b.get());
			//od.setSubtotal(subtotal);
			return orderRepo.save(new OrderDetails(temp,od.getQuantity(), subtotal));
		}
		else 
			throw new BookNotFoundException("Book not found!!");
	}

	@Override
	public OrderDetails updateOrder(OrderDetails od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetails> viewOrderByBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listBestSellingBook() {
		// TODO Auto-generated method stub
		return null;
	}

	public BookOrder calculateTotal(BookOrder bookOrder) {
		// TODO Auto-generated method stub
		Customer ad=customerRepo.findById(bookOrder.getCustomer().getCustomerId()).get();
		double orderTotal=0;
		List<OrderDetails> orderList=orderRepo.findAll();
		for(OrderDetails i: orderList) {
			orderTotal+=i.getSubtotal();
		}
		System.out.println(orderTotal+""+ad);
		BookOrder temp= bookOrderRepo.save(new BookOrder(ad,bookOrder.getOrderDate(),orderTotal,"placed",
				ad.getAddress(),bookOrder.getPaymentMethod(),bookOrder.getRecipientName(),bookOrder.getRecipientPhone()));
		for(OrderDetails i: orderList) {
			i.setBookOrder(temp);
			System.out.println("bo:"+i.getBookOrder().getOrderId()+"oid:"+i.getOrderDetailsId());
			orderRepo.save(i);
			mainOrder.save(new OOrder(i.getBook(), i.getBookOrder(), i.getQuantity(), i.getSubtotal()));
			//mainOrder.save(new OOrder(i.getBook(), i.getQuantity(), i.getSubtotal()));
			orderRepo.deleteAll();
			
		}
		return temp;
	}
	}


