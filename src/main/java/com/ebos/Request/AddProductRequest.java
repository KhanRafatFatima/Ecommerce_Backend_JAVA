package com.ebos.Request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddProductRequest {
	
	private String productTitle;

    private String productDesc;

    private String productSummary;

    private Long productPrice;
    
    private LocalDateTime productCreatedDate;
    
    private LocalDateTime productPublishedDate;
    
    private Long sellerId;

    private Long quantity;
    
    private BigDecimal discount;
    
    private LocalDateTime saleStartsDate;
    
    private LocalDateTime salesEndDate;
	
	private String categoryName;
	
	private String categoryType;
	
	public AddProductRequest() {
		
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductSummary() {
		return productSummary;
	}

	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}

	public Long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}

	public LocalDateTime getProductCreatedDate() {
		return productCreatedDate;
	}

	public void setProductCreatedDate(LocalDateTime productCreatedDate) {
		this.productCreatedDate = productCreatedDate;
	}


	public LocalDateTime getProductPublishedDate() {
		return productPublishedDate;
	}

	public void setProductPublishedDate(LocalDateTime productPublishedDate) {
		this.productPublishedDate = productPublishedDate;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public LocalDateTime getSaleStartsDate() {
		return saleStartsDate;
	}

	public void setSaleStartsDate(LocalDateTime saleStartsDate) {
		this.saleStartsDate = saleStartsDate;
	}

	public LocalDateTime getSalesEndDate() {
		return salesEndDate;
	}

	public void setSalesEndDate(LocalDateTime salesEndDate) {
		this.salesEndDate = salesEndDate;
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

	public AddProductRequest(String productTitle, String productDesc, String productSummary, Long productPrice,
			LocalDateTime productCreatedDate, LocalDateTime productUpdatedDate, LocalDateTime productPublishedDate,
			Long sellerId, Long quantity, BigDecimal discount, LocalDateTime saleStartsDate, LocalDateTime salesEndDate,
			String categoryName, String categoryType) {
		super();
		this.productTitle = productTitle;
		this.productDesc = productDesc;
		this.productSummary = productSummary;
		this.productPrice = productPrice;
		this.productCreatedDate = productCreatedDate;
		this.productPublishedDate = productPublishedDate;
		this.sellerId = sellerId;
		this.quantity = quantity;
		this.discount = discount;
		this.saleStartsDate = saleStartsDate;
		this.salesEndDate = salesEndDate;
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}

	
}