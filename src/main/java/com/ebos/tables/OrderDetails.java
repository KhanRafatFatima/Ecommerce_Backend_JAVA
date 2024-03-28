package com.ebos.tables;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    
    private String status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "Address_id")
    private UserAddress userAddress;

    @OneToOne
    @JoinColumn(name = "Payment_id")
    private Transaction transaction;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_product",
               joinColumns = @JoinColumn(name = "order_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Products> products;

    private LocalDateTime orderdDateTime;
    
    public OrderDetails() {
		
	}

    
    
    
	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public Transaction getTransaction() {
		return transaction;
	}




	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}




	public Set<Products> getProducts() {
		return products;
	}




	public void setProducts(Set<Products> products) {
		this.products = products;
	}




	public OrderDetails(Long orderId, UserAddress userAddress, Transaction transaction,
			LocalDateTime orderdDateTime) {
		super();
		this.orderId = orderId;
		this.userAddress = userAddress;
		this.transaction = transaction;
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

	public Transaction getPayment() {
		return transaction;
	}

	public void setPayment(Transaction transaction) {
		this.transaction = transaction;
	}

	

	

}
