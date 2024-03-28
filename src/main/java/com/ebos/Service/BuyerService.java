package com.ebos.Service;

import java.util.Map;

import com.ebos.Request.UserAddressRequest;
import com.ebos.Request.UserOrderProductRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.GetUserDataResponse;

public interface BuyerService {
	 
	//public ApiResponse userOrderProduct(UserOrderProductRequest userOrderProductRequest);
	
	public ApiResponse userAddAddress(UserAddressRequest userAddressRequest);
	
	public ApiResponse placeOrder(UserOrderProductRequest userOrderProductRequest);
	
    public ApiResponse addPayment(UserOrderProductRequest userOrderProductRequest);
    
    public Map<String, Object> getUserData(); 
    
    public Map<String, Object> getAllProducts();
	

}
