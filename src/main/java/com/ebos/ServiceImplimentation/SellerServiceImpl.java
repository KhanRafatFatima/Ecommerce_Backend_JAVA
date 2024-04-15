
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
import com.ebos.Service.SellerService;
import com.ebos.repository.CategoryRepository;
import com.ebos.repository.ProductRepository;
import com.ebos.repository.SubCategoryRepository;
import com.ebos.repository.UserRepository;
import com.ebos.security.UserPrincipal;
import com.ebos.tables.Category;
import com.ebos.tables.Products;
import com.ebos.tables.SubCategory;
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
	SubCategoryRepository subCategoryRepository;
	
	
	@Override
	public Map<String, Object> addProducts(AddProductRequest addProductRequest) {
	    Map<String, Object> map = new HashMap<>();
	    try {
	        UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	            Optional<SubCategory> categoryOptional = subCategoryRepository.findByCategoryName(addProductRequest.getCategoryName());
	            
	            if(categoryOptional.isPresent()) {
	                SubCategory category = categoryOptional.get();

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

	                product.getCategories().add(category);
	                category.getProducts().add(product);

	                productRepository.save(product);
	                subCategoryRepository.save(category);

	                map.put("status", true);
	                map.put("message", "Product added successfully");
	            } else {
	                map.put("status", false);
	                map.put("message", "Category not found");
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
			
			
	        
			
			
		}catch(Exception e) {
			map.put("status", false);
			map.put("message","Error occured" );
		}
		
		return map;
	}
	
	@Override
	public Map<String,Object> deleteProduct(Long id) {
		Map<String,Object> map=new HashMap<>();
		try {
			UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			Optional<Products> productOptional=productRepository.findById(id);
			
			if(productOptional.isPresent()) {
				Products products=productOptional.get();
				
				productRepository.delete(products);
				
					map.put("status", true);
					map.put("message", "product deleted Successfully");
			
			}else {
				map.put("status", false);
				map.put("message","product not found" );
			}
	       
		}catch(Exception e) {
			map.put("status", false);
			map.put("message","Error occured" );
	    	   
	       }
		return map;
		
	}
	
	

	@Override
	public Map<String,Object> addCategory(AddCategoryRequest addCategoryRequest) {
	    Map<String,Object> map = new HashMap<>();
	    try {
	        UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	            Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());
	            if (userOptional.isPresent()) {
	                Optional<Category> categoryOptional = categoryRepository.findByCategoryTitle(addCategoryRequest.getCategoryTitle());
	                if (categoryOptional.isEmpty()) {
	                    Category category = new Category();    
	                    category.setCategoryTitle(addCategoryRequest.getCategoryTitle());
	                    categoryRepository.save(category);
	                    map.put("status", true);
	                    map.put("message", "Category added successfully");
	                } else {
	                    map.put("status", false);
	                    map.put("message", "Category title already exists");
	                }
	            } else {
	                map.put("status", false);
	                map.put("message", "User not found");
	            }
	        
	    } catch(Exception e) {
	    		e.printStackTrace(); 
	    	    map.put("status", false);
	    	    map.put("message", "Error occurred: " + e.getMessage());
	    }
	    return map;
	}

	
	@Override
	public Map<String, Object> updateCategory(AddCategoryRequest addCategoryRequest,Long id) {
		Map<String,Object> map=new HashMap<>();
		try {
			UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        	Optional<Category> categoryOptional=categoryRepository.findById(id);
	        	
	        	if(categoryOptional.isPresent()) {
	             Category category=categoryOptional.get();
	             
	             	category.setCategoryTitle(addCategoryRequest.getCategoryTitle());
					
					categoryRepository.save(category);
					
					map.put("status", true);
					map.put("message", "category updated successfully");
	             
	        	}else {
	        		map.put("status", false);
					map.put("message", "User not found");
				}
		       
				
			}catch(Exception e) {
				map.put("status", false);
				map.put("message", "Error Occured");
			}
		
		return map;
	}

	@Override
	 public Map<String,Object> deleteCategory(Long id) {
		Map<String,Object> map=new HashMap<>();
		try {
		UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
        	Optional<Category> categoryOptional=categoryRepository.findById(id);
        	
        	if(categoryOptional.isPresent()) {
             Category category=categoryOptional.get();
             
             categoryRepository.delete(category);
             
             	map.put("status", true);
				map.put("message", "category deleted successfully");
             
        	}else {
        		map.put("status", false);
				map.put("message", "Category not found");
			}
	       
	 }catch(Exception e) {
		 	map.put("status", false);
			map.put("message", "Error Occured");
	 }
		
		return map;
	}
	
	@Override
	public Map<String, Object> addSubCategory(AddCategoryRequest addCategoryRequest) {
		Map<String,Object> map=new HashMap<>();
		try {
		UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());
	            if (userOptional.isPresent()) {
	                SubCategory subCategory=new SubCategory();
	              Optional<Category> categoryOptional=categoryRepository.findByCategoryTitle(addCategoryRequest.getCategoryTitle());
	              
	              if(categoryOptional.isPresent()) {
	                
	                subCategory.setCategoryName(addCategoryRequest.getCategoryName());
	                
	                subCategory.setCategory(categoryOptional.get());
	                
	                subCategoryRepository.save(subCategory);
	                
	                map.put("status", true);
                    map.put("message", "SubCategory added successfully");
	                
	              }else {
	            	  map.put("status", false);
	  				  map.put("message", "Category not found");
	              }
	                
	            }else {
	            	map.put("status", false);
					map.put("message", "User not found");
	            }
			
			
		
		
		}catch(Exception e) {
			map.put("status", false);
			map.put("message", "Error Occured");
		}
		
		return map;

	}
	
	@Override
	public Map<String, Object> deleteSubCategory(Long id) {
	    Map<String, Object> map = new HashMap<>();
	    try {
	    	
	    	 UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        Optional<SubCategory> subCategoryOptional = subCategoryRepository.findById(id);
	        if (subCategoryOptional.isPresent()) {
	            subCategoryRepository.delete(subCategoryOptional.get());
	            map.put("status", true);
	            map.put("message", "SubCategory deleted successfully");
	        } else {
	            map.put("status", false);
	            map.put("message", "SubCategory not found");
	        }
		        
	    } catch (Exception e) {
	        map.put("status", false);
	        map.put("message", "Error occurred: " + e.getMessage());
	    }
	    return map;
	}

//	@Override
//	public Map<String, Object> updateSubCategory(AddCategoryRequest addCategoryRequest6) {
//	    Map<String, Object> map = new HashMap<>();
//	    try {
//	        UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {
//	            Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());
//	            if (userOptional.isPresent()) {
//	                Optional<SubCategory> subCategoryOptional = subCategoryRepository.findById(addCategoryRequest.getId());
//	                if (subCategoryOptional.isPresent()) {
//	                    SubCategory subCategory = subCategoryOptional.get();
//	                    Optional<Category> categoryOptional = categoryRepository.findByCategoryTitle(addCategoryRequest.getCategoryTitle());
//	                    if (categoryOptional.isPresent()) {
//	                        // Update the subcategory with the new category
//	                        subCategory.setCategoryName(addCategoryRequest.getCategoryName());
//	                        subCategory.setCategory(categoryOptional.get());
//	                        subCategoryRepository.save(subCategory);
//	                        map.put("status", true);
//	                        map.put("message", "SubCategory updated successfully");
//	                    } else {
//	                        map.put("status", false);
//	                        map.put("message", "Category not found");
//	                    }
//	                } else {
//	                    map.put("status", false);
//	                    map.put("message", "SubCategory not found");
//	                }
//	            } else {
//	                map.put("status", false);
//	                map.put("message", "User not found");
//	            }
//	        } else {
//	            map.put("status", false);
//	            map.put("message", "Access denied. User does not have the required role.");
//	        }
//	    } catch (Exception e) {
//	        map.put("status", false);
//	        map.put("message", "Error occurred: " + e.getMessage());
//	    }	
//	    return map;
//	}




	@Override
	public Map<String, Object> addSales() {
		// TODO Auto-generated method stub
		return null;
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

	

