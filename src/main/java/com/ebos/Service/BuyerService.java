package com.ebos.Service;

import java.util.Map;

import com.ebos.Request.UserAddressRequest;
import com.ebos.Request.UserOrderProductRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.GetUserDataResponse;
import com.ebos.Response.SetListResponse;

public interface BuyerService {
	 
	//public ApiResponse userOrderProduct(UserOrderProductRequest userOrderProductRequest);
	
	public ApiResponse userAddAddress(UserAddressRequest userAddressRequest);
	
	public ApiResponse placeOrder(UserOrderProductRequest userOrderProductRequest);
	
    public ApiResponse addPayment(UserOrderProductRequest userOrderProductRequest);
    
    public Map<String, Object> getUserData(); 
    
    public Map<String,Object> findAllProducts();
	
    public Map<String,Object> getSpecificProduct(String categoryName);

}
