package com.ebos.Request;

public class AddCategoryRequest {
	
	private String categoryName;
	
	private String categoryTitle;
	
	

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public AddCategoryRequest() {
		
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public AddCategoryRequest(String categoryName, String categoryTitle) {
		super();
		this.categoryName = categoryName;
		this.categoryTitle = categoryTitle;
	}

	
	
	
	
}
