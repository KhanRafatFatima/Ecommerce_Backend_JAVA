package com.ebos.Request;

public class AddCategoryRequest {
	
	private String categoryName;
	
	public AddCategoryRequest() {
		
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public AddCategoryRequest(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	
}
