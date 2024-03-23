package com.ebos.Request;

public class AddProductRequest {
	
    private String productName;
	
	private String productDesc;
	
	private String productColor;
	
	private Long productPrice;
	
	//private Long categoryId;
	
	private String categoryName;
	
	private String categoryType;
	
	public AddProductRequest() {
		
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

	public Long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}

//	public Long getCategoryId() {
//		return categoryId;
//	}
//
//	public void setCategoryId(Long categoryId) {
//		this.categoryId = categoryId;
//	}
	
	
	
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

	public AddProductRequest(String productName, String productDesc, String productColor, Long productPrice,
			 String categoryName, String categoryType) {
		super();
		this.productName = productName;
		this.productDesc = productDesc;
		this.productColor = productColor;
		this.productPrice = productPrice;
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}

	


}
