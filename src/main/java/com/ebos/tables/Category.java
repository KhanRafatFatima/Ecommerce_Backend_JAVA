package com.ebos.tables;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	private String categoryName;
	
	private String categoryType;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Products products;
	
	@OneToMany(mappedBy = "category")
	private Set<Products> product = new HashSet<>();
	
	public Category() {
	
	}
	

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}


	public Category(String categoryName, String categoryType, Products products) {
		super();
		this.categoryName = categoryName;
		this.categoryType = categoryType;
		this.products = products;
	}


	public String getCategoryType() {
		return categoryType;
	}


	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
   
	
	
	
}
