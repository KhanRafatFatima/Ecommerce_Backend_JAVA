package com.ebos.tables;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductImages {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageId;
	
	private String images;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id") // Assuming product_id is the foreign key column in ProductImages table
    private Products product;
	
	public ProductImages() {
	}

	public Long getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(Long productImageId) {
		this.productImageId = productImageId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public ProductImages(String images, Products product) {
		super();
		this.images = images;
		this.product = product;
	}
	
	
}
