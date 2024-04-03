package com.ebos.Service;

import java.util.Map;

import com.ebos.Request.AddCategoryRequest;
import com.ebos.Request.AddPaymentTypeRequest;
import com.ebos.Request.AddProductRequest;
import com.ebos.Response.AddCategoryResponse;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteProductAndCategoryResponse;

public interface SellerService {
    
	
	public AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest);
	
	public DeleteProductAndCategoryResponse deleteProduct(Long id);
	
	public DeleteProductAndCategoryResponse deleteCategory(Long id);
	
	public Map<String,Object> updateProduct(AddProductRequest updateProductRequest,Long id);
	
	public Map<String,Object> addProducts(AddProductRequest addProductRequest);
	
	
	//public ApiResponse addpaymentType(AddPaymentTypeRequest addPaymentTypeRequest);
	
	
}
