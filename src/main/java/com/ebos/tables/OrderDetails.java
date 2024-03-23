package com.ebos.tables;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "Address_id")
    private UserAddress userAddress;

    @OneToOne
    @JoinColumn(name = "Payment_id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    private LocalDateTime orderdDateTime;
    
    public OrderDetails() {
		
	}

    
    
	public OrderDetails(Long orderId, UserAddress userAddress, Payment payment, Products products,
			LocalDateTime orderdDateTime) {
		super();
		this.orderId = orderId;
		this.userAddress = userAddress;
		this.payment = payment;
		this.products = products;
		this.orderdDateTime = orderdDateTime;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public LocalDateTime getOrderdDateTime() {
		return orderdDateTime;
	}

	public void setOrderdDateTime(LocalDateTime orderdDateTime) {
		this.orderdDateTime = orderdDateTime;
	}

	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public UserAddress getAddress_Details() {
		return userAddress;
	}

	public void setAddress_Details(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	

}
