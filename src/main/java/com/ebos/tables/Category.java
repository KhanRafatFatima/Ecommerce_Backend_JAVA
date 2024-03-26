package com.ebos.tables;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryTitle;

    private String categoryType;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
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


	
	public String getCategoryTitle() {
		return categoryTitle;
	}


	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
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


	public Category(String categoryTitle, String categoryType, Set<Products> products) {
		super();
		this.categoryTitle = categoryTitle;
		this.categoryType = categoryType;
		this.products = products;
	}


	



	
	
	
}
