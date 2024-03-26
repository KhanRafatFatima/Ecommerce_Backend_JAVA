package com.ebos.tables;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ProductReview {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productReviewId;
	
	private String title;
	
	private double rating;
	
	private LocalDateTime reviewDateTime;
	
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products product;
	
	@OneToMany
   private Set<ProductReviewImages> productReviewImages;
	

	public Long getProductReviewId() {
		return productReviewId;
	}

	public void setProductReviewId(Long productReviewId) {
		this.productReviewId = productReviewId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public LocalDateTime getReviewDateTime() {
		return reviewDateTime;
	}

	public void setReviewDateTime(LocalDateTime reviewDateTime) {
		this.reviewDateTime = reviewDateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public ProductReview(String title, double rating, LocalDateTime reviewDateTime, String content, Products product) {
		super();
		this.title = title;
		this.rating = rating;
		this.reviewDateTime = reviewDateTime;
		this.content = content;
		this.product = product;
	}
	
	
}
