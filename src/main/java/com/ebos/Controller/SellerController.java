package com.ebos.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebos.Request.AddCategoryRequest;
import com.ebos.Request.AddPaymentTypeRequest;
import com.ebos.Request.AddProductRequest;
import com.ebos.Response.AddCategoryResponse;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteProductAndCategoryResponse;
import com.ebos.Response.DeleteResponse;
import com.ebos.Response.SetListResponse;
import com.ebos.Service.SellerService;
import com.ebos.Service.UserService;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/addProduct")
    public ResponseEntity<Map<String,Object>> addProduct(@RequestBody AddProductRequest addProductRequest) {
		Map<String,Object>response =  sellerService.addProducts(addProductRequest);

		return ResponseEntity.ok(response);
    }
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Map<String,Object>> updateProduct(@PathVariable Long id,@RequestBody AddProductRequest updateProductRequest){
		Map<String,Object> response=sellerService.updateProduct(updateProductRequest, id);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/addCategory")
    public ResponseEntity<AddCategoryResponse> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
		AddCategoryResponse response =  sellerService.addCategory(addCategoryRequest);

        // Check the status in the response and return the appropriate HTTP status
        HttpStatus httpStatus = response.getStatus().equalsIgnoreCase("True") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(response, httpStatus);
    }
	
	 @DeleteMapping("/deleteProduct/{id}")
	    public ResponseEntity<DeleteProductAndCategoryResponse> deleteProduct(@PathVariable Long id) {
	        DeleteProductAndCategoryResponse response = sellerService.deleteProduct(id);

	        // Check the status in the response and return the appropriate HTTP status
	        HttpStatus httpStatus = response.getStatus().equalsIgnoreCase("True") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

	        return new ResponseEntity<>(response, httpStatus);
	    }
	 
	 @DeleteMapping("/deleteCategory/{id}")
	    public ResponseEntity<DeleteProductAndCategoryResponse> deleteCategory(@PathVariable Long id) {
	        DeleteProductAndCategoryResponse response = sellerService.deleteCategory(id);

	        // Check the status in the response and return the appropriate HTTP status
	        HttpStatus httpStatus = response.getStatus().equalsIgnoreCase("True") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

	        return new ResponseEntity<>(response, httpStatus);
	    }
	 
//		@PostMapping("/addPaymentType")
//	    public ResponseEntity<ApiResponse> addPaymentType(@RequestBody AddPaymentTypeRequest addPaymentTypeRequest) {
//			ApiResponse response =  sellerService.addpaymentType(addPaymentTypeRequest);
//
//	        // Check the status in the response and return the appropriate HTTP status
//	        HttpStatus httpStatus = response.getStatus().equalsIgnoreCase("True") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//
//	        return new ResponseEntity<>(response, httpStatus);
//	    }
	 
	 
	 
	 
	 
	 
	 
	@GetMapping("/getAllUsers") 
	public SetListResponse findAll() {
		return userService.findAll();
	}
	
	@DeleteMapping("/deleteuser")
    public ResponseEntity<?> deleteUser() {
		
		try {
			return new ResponseEntity<DeleteResponse>(userService.deleteUser(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DeleteResponse>(userService.deleteUser(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
