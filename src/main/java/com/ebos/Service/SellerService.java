package com.ebos.Service;

import com.ebos.Request.AddCategoryRequest;
import com.ebos.Request.AddPaymentTypeRequest;
import com.ebos.Request.AddProductRequest;
import com.ebos.Response.AddCategoryResponse;
import com.ebos.Response.AddProductResponse;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteProductAndCategoryResponse;

public interface SellerService {
    
	public AddProductResponse addProduct(AddProductRequest addProductRequest);
	
	public AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest);
	
	public DeleteProductAndCategoryResponse deleteProduct(Long id);
	
	public DeleteProductAndCategoryResponse deleteCategory(Long id);
	
	//public ApiResponse addpaymentType(AddPaymentTypeRequest addPaymentTypeRequest);
	
	
}
