package com.ebos.Request;

public class AddCategoryRequest {
	
	private String categoryName;
	
	private String categoryType;
	
	public AddCategoryRequest(String categoryName, String categoryType) {
		super();
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public AddCategoryRequest() {
		
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
	
}
