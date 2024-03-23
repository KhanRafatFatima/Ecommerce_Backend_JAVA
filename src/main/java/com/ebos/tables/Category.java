package com.ebos.tables;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    private String categoryType;

    @OneToMany(mappedBy = "category")
    private Set<Products> products;

    // Constructors, getters, and setters...
	
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


	
	public String getCategoryType() {
		return categoryType;
	}


	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
   


	public Set<Products> getProducts() {
		return products;
	}


	public void setProducts(Set<Products> products) {
		this.products = products;
	}


	public Category(String categoryName, String categoryType, Set<Products> products) {
		super();
		this.categoryName = categoryName;
		this.categoryType = categoryType;
		this.products = products;
	}


	



	
	
	
}
