package com.ebos.tables;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@OneToOne
	@JoinColumn(name="Address_id")
	private Address_Details address_Details;
	
	@OneToOne
	@JoinColumn(name="Payment_id")
	private Payment payment;
	
	@OneToOne
	@JoinColumn(name="product_id")
	private Products products;
	
	@OneToOne(mappedBy = "order",cascade= CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	//constructor
	public Order() {
		
	}
	
	

}
