package com.ebos.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductReviewImages {
      
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productReviewImagesId;
	
	private String images;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_review_id")
	private ProductReview productReview;
	
	public ProductReviewImages() {
		
	}

	public Long getProductReviewImagesId() {
		return productReviewImagesId;
	}

	public void setProductReviewImagesId(Long productReviewImagesId) {
		this.productReviewImagesId = productReviewImagesId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	
	

	public ProductReviewImages(String images) {
		super();
		this.images = images;
	}
	
	
	
	
	
	
	
}
