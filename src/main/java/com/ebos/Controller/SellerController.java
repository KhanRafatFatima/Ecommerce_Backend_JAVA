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
import com.ebos.Request.AddProductRequest;
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
    public ResponseEntity<Map<String,Object>> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
		Map<String,Object>response =  sellerService.addCategory(addCategoryRequest);

		return ResponseEntity.ok(response);
    }
	
	@PostMapping("/addSubCategory")
    public ResponseEntity<Map<String,Object>> addSubCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
		Map<String,Object>response =  sellerService.addSubCategory(addCategoryRequest);

		return ResponseEntity.ok(response);
    }
	
	@PostMapping("/updateCategory/{id}")
    public ResponseEntity<Map<String,Object>> updateCategory(@RequestBody AddCategoryRequest addCategoryRequest,@PathVariable Long id) {
		Map<String,Object> response =  sellerService.updateCategory(addCategoryRequest,id);

		return ResponseEntity.ok(response);
    }
	
	
	
	 @DeleteMapping("/deleteProduct/{id}")
	    public ResponseEntity<Map<String,Object>> deleteProduct(@PathVariable Long id) {
		 Map<String,Object> response = sellerService.deleteProduct(id);
		 
		 return ResponseEntity.ok(response);
	    }
	 
	 @DeleteMapping("/deleteCategory/{id}")
	    public ResponseEntity<Map<String,Object>> deleteCategory(@PathVariable Long id) {
		 Map<String,Object>  response = sellerService.deleteCategory(id);

		 return ResponseEntity.ok(response);
	       
	    }
	 
	 @DeleteMapping("/deleteSubCategory/{id}")
	    public ResponseEntity<Map<String,Object>> deleteSubCategory(@PathVariable Long id) {
		 Map<String,Object> response = sellerService.deleteSubCategory(id);
		 
		 return ResponseEntity.ok(response);
	    }
	 
//	 @PutMapping("/updateSubCategory/{id}")
//		public ResponseEntity<Map<String,Object>> updateSubCategory(@PathVariable Long id,@RequestBody AddProductRequest updateProductRequest){
//			Map<String,Object> response=sellerService.updateSubCategory(updateProductRequest, id);
//			
//			return ResponseEntity.ok(response);
//		}
	 
//		@PostMapping("/addPaymentType")
//	    public ResponseEntity<ApiResponse> addPaymentType(@RequestBody AddPaymentTypeRequest addPaymentTypeRequest) {
//			ApiResponse response =  sellerService.addpaymentType(addPaymentTypeRequest);
//
//	        // Check the status in the response and return the appropriate HTTP status
//	        HttpStatus httpStatus = response.getStatus().equalsIgnoreCase("True") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//
//	        return new ResponseEntity<>(response, httpStatus);
//	    }
	 
}
