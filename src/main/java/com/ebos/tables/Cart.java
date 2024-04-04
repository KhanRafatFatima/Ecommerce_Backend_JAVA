package com.ebos.tables;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Cart {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    private String quantity;

    private LocalDateTime createddate;
    
    private LocalDateTime modifieddate;
	
	public Cart() {
	
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
	public LocalDateTime getCreateddate() {
		return createddate;
	}

	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}

	public LocalDateTime getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(LocalDateTime modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Cart(User user, Products products, String quantity, LocalDateTime createddate, LocalDateTime modifieddate) {
		super();
		this.user = user;
		this.products = products;
		this.quantity = quantity;
		this.createddate = createddate;
		this.modifieddate = modifieddate;
	}

	
}
