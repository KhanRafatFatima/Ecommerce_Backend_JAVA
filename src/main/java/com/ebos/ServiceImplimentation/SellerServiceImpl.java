package com.ebos.ServiceImplimentation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebos.Request.AddCategoryRequest;
import com.ebos.Request.AddPaymentTypeRequest;
import com.ebos.Request.AddProductRequest;
import com.ebos.Response.AddCategoryResponse;
import com.ebos.Response.AddProductResponse;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteProductAndCategoryResponse;
import com.ebos.Service.SellerService;
import com.ebos.repository.CategoryRepository;
import com.ebos.repository.PaymentTypeRepository;
import com.ebos.repository.ProductRepository;
import com.ebos.repository.UserRepository;
import com.ebos.security.UserPrincipal;
import com.ebos.tables.Category;
import com.ebos.tables.PaymentType;
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
	
	@Autowired
	PaymentTypeRepository paymentTypeRepository;

	@Override
	public AddProductResponse addProduct(AddProductRequest addProductRequest) {
	    AddProductResponse addProductResponse=new AddProductResponse();
	    
	    try {
	    	  UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		        // Check if the authenticated user has the "seller" role
		        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {

		            // Fetch the authenticated user details from the database
		            Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());

		            	if(userOptional.isPresent()) {
		    				Optional<Category> categoryOptional=categoryRepository.findByCategoryType(addProductRequest.getCategoryType());
		    				//System.out.println("------------>"+categoryOptional);
		    	          if(categoryOptional.isPresent()) {
		    	        	  Products products=new Products();
				              Category category=new Category();
				              Category category2=new Category();
				                products.setProductName(addProductRequest.getProductName());
				                products.setProductColor(addProductRequest.getProductColor());
				                products.setProductPrice(addProductRequest.getProductPrice());
				                products.setProductDesc(addProductRequest.getProductDesc());
				                // Set category using categoryId from the request
				                category.setCategoryName(addProductRequest.getCategoryName());
				                category.setCategoryType(addProductRequest.getCategoryType());
				                
				                
				                
				                //category.setCategoryId(addProductRequest.getCategoryId());
				                //products.setCategoryType(category2.getCategoryType());
				                
				                productRepository.save(products);
				                addProductResponse.setMessage("Product Added Successfully!");
					        	addProductResponse.setStatus("True");
		    	          }else {
		    	        	    addProductResponse.setMessage("Categorytype is not present");
					        	addProductResponse.setStatus("false");
		    	          }
		                
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
		        	System.out.print("----------->" +e);
		        	addProductResponse.setMessage("Error Occurrred");
		        	addProductResponse.setStatus("False");
	    	
	    }
	    
		return addProductResponse;
	}
	
	@Override
	public DeleteProductAndCategoryResponse deleteProduct(Long id) {
		DeleteProductAndCategoryResponse deleteProductAndCategoryResponse=new DeleteProductAndCategoryResponse();
		try {
			UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			  // Check if the authenticated user has the "Admin" role
	        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {
			
	        
			Optional<Products> productOptional=productRepository.findById(id);
			
			if(productOptional.isPresent()) {
				Products products=productOptional.get();
				
				productRepository.delete(products);
				
				deleteProductAndCategoryResponse.setMessage("Deleted the product, Successfully!");
				deleteProductAndCategoryResponse.setStatus("True");
			
			}else {
				deleteProductAndCategoryResponse.setMessage("Product with this id is not present");
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
				category.setCategoryType(addCategoryRequest.getCategoryType());
				
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
	public DeleteProductAndCategoryResponse deleteCategory(Long id) {
		DeleteProductAndCategoryResponse deleteCategory= new DeleteProductAndCategoryResponse();
		try {
		UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		// Check if the authenticated user has the "Admin" role
        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {
        	Optional<Category> categoryOptional=categoryRepository.findById(id);
        	
        	if(categoryOptional.isPresent()) {
             Category category=categoryOptional.get();
             
             categoryRepository.delete(category);
             
             deleteCategory.setMessage("Deleted the category, Successfully");
     		 deleteCategory.setStatus("Success");	
        	}else {
        		deleteCategory.setMessage("Category with this id is not present");
        		deleteCategory.setStatus("False");
        	}
        	
        }else{
        	deleteCategory.setMessage("Access denied. User does not have the required role.");
        	deleteCategory.setStatus("False");
        	}
	 }catch(Exception e) {
		deleteCategory.setMessage("Error Occurrred");
		deleteCategory.setStatus("False");
	 }
		
		return deleteCategory;
	}

	@Override
	public ApiResponse addpaymentType(AddPaymentTypeRequest addPaymentTypeRequest) {
	     ApiResponse addPaymentResponse=new ApiResponse();
	     try {
	    	 UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    		// Check if the authenticated user has the "Admin" role
	         if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {
	    	   Optional<User> userOptional=userRepository.findById(authenticatedUser.getId());
	    	   if(userOptional.isPresent()) {
                  PaymentType paymentType=new PaymentType();
                  
                   paymentType.setPaymentType(addPaymentTypeRequest.getPaymentType());
                   
                   paymentTypeRepository.save(paymentType);
                   
                   addPaymentResponse.setMessage("PaymentType Added, Successfully");
                   addPaymentResponse.setStatus("Success");
                  
	    	   }else {
	    		   addPaymentResponse.setMessage("User is not present");
	    		   addPaymentResponse.setStatus("False");
	    	   }
	        	 
	         }else {
	        	 addPaymentResponse.setMessage("Access denied. User does not have the required role.");
	        	 addPaymentResponse.setStatus("False");
	         }
	     }catch(Exception e) {
	    	 addPaymentResponse.setMessage("Error Occurrred");
	    	 addPaymentResponse.setStatus("False");
	     }
	     
	     return addPaymentResponse;
	}

	
}
