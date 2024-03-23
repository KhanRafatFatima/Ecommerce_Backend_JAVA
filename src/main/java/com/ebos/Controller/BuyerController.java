package com.ebos.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebos.Request.AddProductRequest;
import com.ebos.Request.UserAddressRequest;
import com.ebos.Request.UserOrderProductRequest;
import com.ebos.Response.AddProductResponse;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.GetUserDataResponse;
import com.ebos.Service.BuyerService;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
	
	@Autowired
	BuyerService buyerService;
	
	 @GetMapping("/getUserData")
	    public ResponseEntity<?> getUserData() {
	        try {
	            Map<String, Object> userData = buyerService.getUserData();
	            return ResponseEntity.ok(userData);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	
	 @PostMapping("/addPayment")
	    public ResponseEntity<ApiResponse> addPayment(@RequestBody UserOrderProductRequest addPaymentRequest) {
	        ApiResponse apiResponse = buyerService.addPayment(addPaymentRequest);
	        HttpStatus httpStatus = apiResponse.getStatus().equals("Success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
	        return new ResponseEntity<>(apiResponse, httpStatus);
	    }

	    @PostMapping("/placeOrder")
	    public ResponseEntity<ApiResponse> placeOrder(@RequestBody UserOrderProductRequest orderProductRequest) {
	        ApiResponse apiResponse = buyerService.placeOrder(orderProductRequest);
	        HttpStatus httpStatus = apiResponse.getStatus().equals("Success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
	        return new ResponseEntity<>(apiResponse, httpStatus);
	    }
	  
	 @PostMapping("/addAddress")
	    public ResponseEntity<ApiResponse> addUserAddress(@RequestBody UserAddressRequest userAddressRequest) {
	        ApiResponse apiResponse = buyerService.userAddAddress(userAddressRequest);
	        return ResponseEntity.ok(apiResponse);
	    }
	 
//	 @PostMapping("/userOrderProduct")
//	    public ResponseEntity<ApiResponse> userOrderProduct(@RequestBody UserOrderProductRequest userOrderProductRequest) {
//	        ApiResponse orderResponse = buyerService.userOrderProduct(userOrderProductRequest);
//
//	        HttpStatus httpStatus = HttpStatus.OK;
//
//	        if ("Failed".equals(orderResponse.getStatus()) || "False".equals(orderResponse.getStatus())) {
//	            httpStatus = HttpStatus.BAD_REQUEST; // You might want to choose a different HTTP status code based on your use case
//	        }
//
//	        return new ResponseEntity<>(orderResponse, httpStatus);
//	    }
	

}
