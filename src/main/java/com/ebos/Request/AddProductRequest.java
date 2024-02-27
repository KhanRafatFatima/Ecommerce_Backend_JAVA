package com.ebos.Request;

public class AddProductRequest {
	
    private String productName;
	
	private String productDesc;
	
	private String productColor;
	
	private Long productPrice;
	
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

	public AddProductRequest(String productName, String productDesc, String productColor, Long productPrice) {
		super();
		this.productName = productName;
		this.productDesc = productDesc;
		this.productColor = productColor;
		this.productPrice = productPrice;
	}
	
	

}
