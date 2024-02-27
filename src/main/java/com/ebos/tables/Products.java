package com.ebos.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private String productName;
	
	private String productDesc;
	
	private String productColor;
	
	private Long productPrice;
    
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	public Products() {
		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public Long  getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long  productPrice) {
		this.productPrice = productPrice;
	}

	public Products(String productName, String productDesc, String productColor,Long  productPrice) {
		super();
		this.productName = productName;
		this.productDesc = productDesc;
		this.productColor = productColor;
		this.productPrice = productPrice;
	}
	
	

}
