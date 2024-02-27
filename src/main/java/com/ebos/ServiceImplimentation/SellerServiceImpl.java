package com.ebos.ServiceImplimentation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebos.Request.AddCategoryRequest;
import com.ebos.Request.AddProductRequest;
import com.ebos.Response.AddCategoryResponse;
import com.ebos.Response.AddProductResponse;
import com.ebos.Response.DeleteProductAndCategoryResponse;
import com.ebos.Service.SellerService;
import com.ebos.repository.CategoryRepository;
import com.ebos.repository.ProductRepository;
import com.ebos.repository.UserRepository;
import com.ebos.security.UserPrincipal;
import com.ebos.tables.Category;
import com.ebos.tables.Products;
import com.ebos.tables.User;

@Service
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public AddProductResponse addProduct(AddProductRequest addProductRequest) {
	    AddProductResponse addProductResponse=new AddProductResponse();
	    try {
	    	  UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		        // Check if the authenticated user has the "seller" role
		        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {

		            // Fetch the authenticated user details from the database
		            Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());

		            if (userOptional.isPresent()) {
		                Products products=new Products();
		                
		                products.setProductName(addProductRequest.getProductName());
		                products.setProductColor(addProductRequest.getProductColor());
		                products.setProductPrice(addProductRequest.getProductPrice());
		                products.setProductDesc(addProductRequest.getProductDesc());
		                
		                productRepository.save(products); 
		                
		                addProductResponse.setMessage("Product Added Successfully!");
			        	addProductResponse.setStatus("True");
		            
		            }else {
		            	addProductResponse.setMessage("User is not present");
			        	addProductResponse.setStatus("False");
		            	
		            }
	    	
		        }else {
		        	addProductResponse.setMessage("Access denied. User does not have the required role.");
		        	addProductResponse.setStatus("False");
		        }
		        }catch(Exception e) {
		        	addProductResponse.setMessage("Error Occurrred");
		        	addProductResponse.setStatus("False");
	    	
	    }
	    
		return addProductResponse;
	}

	@Override
	public AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
		AddCategoryResponse addCategoryResponse=new AddCategoryResponse();	
		try {
			UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			  // Check if the authenticated user has the "Admin" role
	        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {
			
			Optional<User> userOptional=userRepository.findById(authenticatedUser.getId());
			
			if(userOptional.isPresent()) {
				Category category=new Category();
				
				category.setCategoryName(addCategoryRequest.getCategoryName());
				
				categoryRepository.save(category);
				
				addCategoryResponse.setMessage("Category Added Successfully!");
				addCategoryResponse.setStatus("True");
				
			}else {
				addCategoryResponse.setMessage("User is not present");
				addCategoryResponse.setStatus("False");
			}
	       }else {
	    	   addCategoryResponse.setMessage("Access denied. User does not have the required role.");
	    	   addCategoryResponse.setStatus("False");
	       }
			
		}catch(Exception e) {
			addCategoryResponse.setMessage("Error Occurrred");
			addCategoryResponse.setStatus("False");
		}
		
		return addCategoryResponse;
		}

	@Override
	public DeleteProductAndCategoryResponse deleteProduct(Long id) {
		DeleteProductAndCategoryResponse deleteProductAndCategoryResponse=new DeleteProductAndCategoryResponse();
		try {
			UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			  // Check if the authenticated user has the "Admin" role
	        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {
			
			Optional<User> userOptional=userRepository.findById(authenticatedUser.getId());
			
			if(userOptional.isPresent()) {
				Products products=new Products();
				
				productRepository.delete(products);
				
				deleteProductAndCategoryResponse.setMessage("Category Added Successfully!");
				deleteProductAndCategoryResponse.setStatus("True");
			
			}else {
				deleteProductAndCategoryResponse.setMessage("User is not present");
				deleteProductAndCategoryResponse.setStatus("False");
			}
	       }else {
	    	   deleteProductAndCategoryResponse.setMessage("Access denied. User does not have the required role.");
	    	   deleteProductAndCategoryResponse.setStatus("False"); 
	       }
		}catch(Exception e) {
			deleteProductAndCategoryResponse.setMessage("Error Occurrred");
			deleteProductAndCategoryResponse.setStatus("False");
	    	   
	       }
		return deleteProductAndCategoryResponse;
		
	}
}
