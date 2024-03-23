package com.ebos.tables;

import jakarta.persistence.*;
import java.util.Set;

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
    @JoinColumn(name = "category_type")
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
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Products(String productName, String productDesc, String productColor, Long productPrice, Category category) {
		super();
		this.productName = productName;
		this.productDesc = productDesc;
		this.productColor = productColor;
		this.productPrice = productPrice;
		this.category = category;
	}


	
	

}
