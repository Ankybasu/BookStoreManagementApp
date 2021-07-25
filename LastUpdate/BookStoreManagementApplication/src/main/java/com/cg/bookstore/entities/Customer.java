package com.cg.bookstore.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name="customertable")
public class Customer {
	
	@Id
	@SequenceGenerator(name="custSeqGen",sequenceName="custSeq",initialValue=201,allocationSize=100)
	@GeneratedValue(generator="custSeqGen")
	@Column(name="cust_id")
	private int customerId;
	
	
	@Column(name="email")
	private String email;
	
	@Column(name="fullname")
	private String fullName;

	@Column(name="password")
	private String password;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="registration_date")
	private LocalDate registerOn;
	
	public Customer() {
		super();
	}

	public Customer(String email, String fullName, String password, Address address, String mobileNumber,
			LocalDate registerOn) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.registerOn = registerOn;
	}
	

	public Customer(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDate getRegisterOn() {
		return registerOn;
	}

	public void setRegisterOn(LocalDate registerOn) {
		this.registerOn = registerOn;
	}

	
}
