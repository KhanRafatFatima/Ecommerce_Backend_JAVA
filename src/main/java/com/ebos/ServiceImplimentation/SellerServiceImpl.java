package com.ebos.ServiceImplimentation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebos.Request.AddCategoryRequest;
import com.ebos.Request.AddProductRequest;
import com.ebos.Response.AddCategoryResponse;
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
	public Map<String, Object> addProducts(AddProductRequest addProductRequest) {
	    Map<String, Object> map = new HashMap<>();
	    try {
	        UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	        // Check if the authenticated user has the "SELLER" role
	        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {

	            // Create a new product instance
	            Products product = new Products();
	            product.setProductTitle(addProductRequest.getProductTitle());
	            product.setProductDesc(addProductRequest.getProductDesc());
	            product.setProductSummary(addProductRequest.getProductSummary());
	            product.setProductPrice(addProductRequest.getProductPrice());
	            product.setProductCreatedDate(LocalDateTime.now());
	            product.setProductPublishedDate(LocalDateTime.now());
	            product.setSellerId(authenticatedUser.getId());
	            product.setQuantity(addProductRequest.getQuantity());
	            product.setDiscount(addProductRequest.getDiscount());
	            product.setSales(addProductRequest.isSales());
	            if (product.isSales()) {
	                product.setSaleStartsDate(addProductRequest.getSaleStartsDate());
	                product.setSalesEndDate(addProductRequest.getSalesEndDate());
	            }

	            // Save the product to the database
	            Products savedProduct = productRepository.save(product);

	            // Fetch the category by name
	            Optional<Category> categoryOptional = categoryRepository.findByCategoryName(addProductRequest.getCategoryName());
	            if (categoryOptional.isPresent()) {
	                Category category = categoryOptional.get();

	                // Map the relationship between the product and the category
	                savedProduct.getCategories().add(category);
	                category.getProducts().add(savedProduct);

	                productRepository.save(savedProduct);
	                categoryRepository.save(category);

	                map.put("status", true);
	                map.put("message", "Product added successfully");
	            } else {
	                map.put("status", false);
	                map.put("message", "Category not found");
	            }
	        } else {
	            map.put("status", false);
	            map.put("message", "Access denied. User does not have the required role.");
	        }
	    } catch (Exception e) {
	        map.put("status", false);
	        map.put("message", "Error occurred: " + e.getMessage());
	    }

	    return map;
	}

	
	
	@Override
	public Map<String, Object> updateProduct(AddProductRequest updateProductRequest,Long id) {
		Map<String,Object> map=new HashMap<>();
		try {
			UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			  // Check if the authenticated user has the "Admin" role
	        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {
			
	        
			Optional<Products> productOptional=productRepository.findById(id);
			
			if(productOptional.isPresent()) {
				Products products=productOptional.get();
				
				  products.setProductTitle(updateProductRequest.getProductTitle());
	              products.setProductDesc(updateProductRequest.getProductDesc());
	              products.setProductSummary(updateProductRequest.getProductSummary());
	              products.setProductPrice(updateProductRequest.getProductPrice());
	              products.setProductCreatedDate(LocalDateTime.now());
	              products.setProductPublishedDate(LocalDateTime.now());
	              products.setSellerId(authenticatedUser.getId());
	              products.setQuantity(updateProductRequest.getQuantity());
	              products.setDiscount(updateProductRequest.getDiscount());
	              products.setSales(updateProductRequest.isSales());
	              if(products.isSales()) {
	              products.setSaleStartsDate(updateProductRequest.getSaleStartsDate());
	              products.setSalesEndDate(updateProductRequest.getSalesEndDate());
	              }
	              
	              productRepository.save(products);
	              
	              map.put("status", true);
	              map.put("message", "product updated Successfully");
				
				
			}else {
				map.put("status", false);
				map.put("message","product not found" );
			}
			
			
	        }else {
	        	map.put("status", false);
				map.put("message","user doesnt have required Role" );
	        }
			
			
		}catch(Exception e) {
			map.put("status", false);
			map.put("message","Error occured" );
		}
		
		return map;
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
				
				category.setCategoryTitle(addCategoryRequest.getCategoryTitle());
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

	



	
}
//	@Override
//	public ApiResponse addpaymentType(AddPaymentTypeRequest addPaymentTypeRequest) {
//	     ApiResponse addPaymentResponse=new ApiResponse();
//	     try {
//	    	 UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	    		// Check if the authenticated user has the "Admin" role
//	         if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {
//	    	   Optional<User> userOptional=userRepository.findById(authenticatedUser.getId());
//	    	   if(userOptional.isPresent()) {
//                  PaymentType paymentType=new PaymentType();
//                  
//                   paymentType.setPaymentType(addPaymentTypeRequest.getPaymentType());
//                   
//                   paymentTypeRepository.save(paymentType);
//                   
//                   addPaymentResponse.setMessage("PaymentType Added, Successfully");
//                   addPaymentResponse.setStatus("Success");
//                  
//	    	   }else {
//	    		   addPaymentResponse.setMessage("User is not present");
//	    		   addPaymentResponse.setStatus("False");
//	    	   }
//	        	 
//	         }else {
//	        	 addPaymentResponse.setMessage("Access denied. User does not have the required role.");
//	        	 addPaymentResponse.setStatus("False");
//	         }
//	     }catch(Exception e) {
//	    	 addPaymentResponse.setMessage("Error Occurrred");
//	    	 addPaymentResponse.setStatus("False");
//	     }
//	     
//	     return addPaymentResponse;
//	}

	

