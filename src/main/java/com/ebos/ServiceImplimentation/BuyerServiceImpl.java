
package com.ebos.ServiceImplimentation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebos.Request.AddProductToCartRequest;
import com.ebos.Request.UserAddressRequest;
import com.ebos.Request.UserOrderProductRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.SetListResponse;
import com.ebos.Service.BuyerService;
import com.ebos.repository.CartRepository;
import com.ebos.repository.CategoryRepository;
import com.ebos.repository.Order_DetailsRepository;
import com.ebos.repository.PaymentRepository;
import com.ebos.repository.ProductRepository;
import com.ebos.repository.UserAddressRepository;
import com.ebos.repository.UserRepository;
import com.ebos.security.UserPrincipal;
import com.ebos.tables.Cart;
import com.ebos.tables.Category;
import com.ebos.tables.OrderDetails;
import com.ebos.tables.Transaction;
import com.ebos.tables.Products;
import com.ebos.tables.User;
import com.ebos.tables.UserAddress;

@Service
public class BuyerServiceImpl implements BuyerService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Order_DetailsRepository order_DetailsRepository;
	
	@Autowired
	UserAddressRepository userAddressRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	


	
	@Override
	public Map<String,Object> findAllProducts() {
		Map<String,Object> map=new HashMap<>();
		try {
		List<Products> list=productRepository.findAll();
		
		map.put("List", list);
		map.put("status", true);
		map.put("message", "Successfully fetched the data");
		}catch (Exception e) {
			map.put("status", false);
			map.put("message", "error occured");
		}
		return map;
	}
	
	@Override
	public Map<String, Object> getSpecificProduct(String categoryName) {
	   Map<String,Object> map=new HashMap<>();
	   try {
		   //Optional<Category> categoryOptional=categoryRepository.findByCategoryName(categoryName);
		   
		   
		   
	   }catch(Exception e) {
		   
	   }
	   return map;
	}

	
	@Override
	public ApiResponse userAddAddress(UserAddressRequest userAddressRequest) {
	    ApiResponse apiResponse = new ApiResponse();
	    try {
	        UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());

	        if (userOptional.isPresent()) {
	            User user = userOptional.get();

	            UserAddress userAddress = new UserAddress();
	            userAddress.setCity(userAddressRequest.getCity());
	            userAddress.setLandMark(userAddressRequest.getLandMark());
	            userAddress.setPincode(userAddressRequest.getPincode());
	            userAddress.setStreetAddress(userAddressRequest.getStreetAddress());
	            userAddress.setState(userAddressRequest.getState());

	            // Set the User instance in User_Address
	            userAddress.setUser(user);

	            // Save only the User_Address
	            userAddressRepository.save(userAddress);

	            apiResponse.setStatus("Success");
	            apiResponse.setMessage("Address added Successfully");
	        } else {
	            apiResponse.setStatus("Failed");
	            apiResponse.setMessage("User not found");
	        }
	    } catch (Exception e) {
	    	System.out.println("--------->" +e);
	        apiResponse.setStatus("Failed");
	        apiResponse.setMessage("Error Occurred: " + e.getMessage());
	    }

	    return apiResponse;
	}
	

	 @Override
	    public ApiResponse addPayment(UserOrderProductRequest userOrderProductRequest) {
	        ApiResponse apiResponse = new ApiResponse();
	        try {
	            Optional<UserPrincipal> authenticatedUser = Optional.ofNullable((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	            if (authenticatedUser.isPresent()) {
	                Optional<User> userOptional = userRepository.findById(authenticatedUser.get().getId());
	                if (userOptional.isPresent()) {
	                    User user = userOptional.get();
	                    Optional<Products> productOptional = productRepository.findById(userOrderProductRequest.getProductId());
	                    if (productOptional.isPresent()) {
	                        Products product = productOptional.get();

	                        Transaction transaction = new Transaction();
	                        transaction.setAmount(product.getProductPrice()); // Assuming product has a getPrice() method
	                        //transaction.setPaymentDate(LocalDateTime.now());
	                        transaction.setStatus(userOrderProductRequest.getStatus());

	                        //Optional<PaymentType> paymentTypeOptional = paymentTypeRepository.findById(userOrderProductRequest.getPaymentTypeId());
	                        //transaction.setPaymentType(paymentTypeOptional.orElse(null)); // Set payment type if found

	                        transaction.setUser(user);

	                        paymentRepository.save(transaction);

	                        apiResponse.setStatus("Success");
	                        apiResponse.setMessage("Payment added Successfully");
	                    } else {
	                        apiResponse.setStatus("Failed");
	                        apiResponse.setMessage("Product not found");
	                    }
	                } else {
	                    apiResponse.setStatus("Failed");
	                    apiResponse.setMessage("User not found");
	                }
	            } else {
	                apiResponse.setStatus("Failed");
	                apiResponse.setMessage("Authentication failed");
	            }
	        } catch (Exception e) {
	            System.out.println("--------->" + e);
	            apiResponse.setStatus("Failed");
	            apiResponse.setMessage("Error Occurred: " + e.getMessage());
	        }
	        return apiResponse;
	    }

	    @Override
	    public ApiResponse placeOrder(UserOrderProductRequest userOrderProductRequest) {
	        ApiResponse apiResponse = new ApiResponse();
	        try {
	            Optional<UserPrincipal> authenticatedUser = Optional.ofNullable((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	            if (authenticatedUser.isPresent()) {
	                Optional<User> userOptional = userRepository.findById(authenticatedUser.get().getId());
	                if (userOptional.isPresent()) {
	                    User user = userOptional.get();
	                    Optional<Products> productOptional = productRepository.findById(userOrderProductRequest.getProductId());
	                    if (productOptional.isPresent()) {
	                        Products product = productOptional.get();

	                        OrderDetails orderDetails = new OrderDetails();
	                       // orderDetails.setProducts(product);
	                        orderDetails.setOrderdDateTime(LocalDateTime.now());

	                        // Assuming you have a one-to-one mapping between User and UserAddress
	                        UserAddress userAddress = user.getAddress_Details();
	                        orderDetails.setAddress_Details(userAddress);

	                        Optional<Transaction> paymentOptional = paymentRepository.findById(userOrderProductRequest.getPaymentId());
	                        orderDetails.setPayment(paymentOptional.orElse(null)); // Set payment if found

	                        order_DetailsRepository.save(orderDetails);

	                        apiResponse.setStatus("Success");
	                        apiResponse.setMessage("Order placed Successfully");
	                    } else {
	                        apiResponse.setStatus("Failed");
	                        apiResponse.setMessage("Product not found");
	                    }
	                } else {
	                    apiResponse.setStatus("Failed");
	                    apiResponse.setMessage("User not found");
	                }
	            } else {
	                apiResponse.setStatus("Failed");
	                apiResponse.setMessage("Authentication failed");
	            }
	        } catch (Exception e) {
	            System.out.println("--------->" + e);
	            apiResponse.setStatus("Failed");
	            apiResponse.setMessage("Error Occurred: " + e.getMessage());
	        }
	        return apiResponse;
	    }


		@Override
		public Map<String, Object> findAllCategory() {
			// TODO Auto-generated method stub
			return null;
		}

      /////////two things should be added if the product same is already present in cart then just increment the quantity of product 
//		@Override
//		public Map<String, Object> addProductToCart(AddProductToCartRequest addProductToCartRequest) {
//		    Map<String, Object> map = new HashMap<>();
//		    try {
//		        UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		        Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());
//
//		        List<Products> productsList = productRepository.findByProductTitle(addProductToCartRequest.getProductTitle());
//
//		        if (userOptional.isPresent()) {
//		            if (!productsList.isEmpty()) {
//		                // If there are multiple products with the same title, select the first one
//		                Products product = productsList.get(0);
//
//		                Cart cart = new Cart();
//		                cart.setQuantity(addProductToCartRequest.getQuantity());
//		                cart.setProducts(product);
//		                cart.setUser(userOptional.get());
//		                cart.setCreateddate(LocalDateTime.now());
//                        cart.setModifieddate(LocalDateTime.now());
//		                cartRepository.save(cart);
//
//		                map.put("status", true);
//		                map.put("message", "Added to Cart");
//		            } else {
//		                map.put("status", false);
//		                map.put("message", "Product not found");
//		            }
//		        } else {
//		            map.put("status", false);
//		            map.put("message", "User not found");
//		        }
//		    } catch (Exception e) {
//		        map.put("status", false);
//		        System.out.println("Error occurred: " + e);
//		        map.put("message", "Error occurred");
//		    }
//		    return map;
//		}
//
//
//		@Override
//		public Map<String, Object> deleteProductFromCart(Long id) {
//			Map<String,Object> map=new HashMap<>();
//			try {
//				UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//				
//		        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("BUYER"))) {
//				
//		  
//		        	
//				Optional<Cart> cartOptional=cartRepository.findById(id);
//				
//				if(cartOptional.isPresent()) {
//					Cart cart=cartOptional.get();
//					
//					cartRepository.delete(cart);
//					
//						map.put("status", true);
//						map.put("message", "Removed from cart Successfully");
//				
//				}else {
//					map.put("status", false);
//					map.put("message","product not found" );
//				}
//		       }else {
//		    	    map.put("status", false);
//					map.put("message","user doesnt have required Role" );
//		       }
//			}catch(Exception e) {
//				map.put("status", false);
//				map.put("message","Error occured" );
//		    	   
//		       }
//			return map;
//
//		}


}

	
	
	