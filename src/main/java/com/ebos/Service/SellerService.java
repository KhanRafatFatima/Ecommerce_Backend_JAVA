package com.ebos.Service;

import java.util.Map;

import com.ebos.Request.AddCategoryRequest;
import com.ebos.Request.AddProductRequest;

public interface SellerService {
	public Map<String,Object> addProducts(AddProductRequest addProductRequest);
	
	public Map<String,Object> updateProduct(AddProductRequest updateProductRequest,Long id);
	
	public Map<String,Object> deleteProduct(Long id);
	
	public Map<String,Object> addCategory(AddCategoryRequest addCategoryRequest);
	
	public Map<String,Object> updateCategory(AddCategoryRequest addCategoryRequest,Long id);
	
	public Map<String,Object> deleteCategory(Long id);
	
	
	
	
	
	
	//public ApiResponse addpaymentType(AddPaymentTypeRequest addPaymentTypeRequest);
	
	
}
