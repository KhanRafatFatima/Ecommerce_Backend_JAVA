
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

import com.ebos.Request.UserAddressRequest;
import com.ebos.Request.UserOrderProductRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Service.BuyerService;
import com.ebos.repository.Order_DetailsRepository;
import com.ebos.repository.PaymentRepository;
import com.ebos.repository.ProductRepository;
import com.ebos.repository.UserAddressRepository;
import com.ebos.repository.UserRepository;
import com.ebos.security.UserPrincipal;
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
	
	


	@Override
	public Map<String, Object> getUserData() {
		 Map<String, Object> map = new HashMap<>();
		 List<Map<String, Object>> list = new ArrayList<>();
		 
		 try {
			 Map<String, Object> tempMap=new HashMap<>();
			 UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 Optional<User> userOptional=userRepository.findById(authenticatedUser.getId());
			 if(userOptional.isPresent()) {
				 User user=userOptional.get();
				 
				 tempMap.put("Name", user.getFirstname());
				 tempMap.put("MiddleName", user.getLastname());
				 tempMap.put("LastName", user.getMiddlename());
				 tempMap.put("Username",user.getUsername());
				 tempMap.put("Email", user.getEmail());
				 tempMap.put("mobileNo", user.getMobileNo());
				// Check if the authenticated user has the "Seller" role
			        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("SELLER"))) {
				 tempMap.put("Profile",user.getProfile());
				 tempMap.put("Intro", user.getIntro());
			        }

				 list.add(tempMap);
				 
				 map.put("UserData", list);
				 map.put("message", "success");
		         map.put("status", true);
				 
			 }else {
				 	map.put("records", list);
		            map.put("message", "User not found");
		            map.put("status", false);
		        }

		    } catch (Exception e) {
		        map.put("records", list);
		        map.put("message", "Error retrieving user data");
		        map.put("status", false);
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
	public Map<String, Object> getAllProducts() {
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> list=new ArrayList<>();
		try {
			Map<String, Object> tempMap=new HashMap<>();
			Products products=new Products();
			
			tempMap.put("Title",products.getProductTitle());
			tempMap.put("Price", products.getProductPrice());
			tempMap.put("Quantity",products.getQuantity());
			tempMap.put("Description", products.getProductDesc());
			tempMap.put("CreatedDate", products.getProductCreatedDate());
			tempMap.put("PublishedDate", products.getProductPublishedDate());
			tempMap.put("Image", products.getProductBannerImage());
			tempMap.put("Summmary", products.getProductSummary());
			if(products.isSales()) {
			tempMap.put("", products.getSaleStartsDate());
			tempMap.put("", products.getSalesEndDate());
			}
			tempMap.put("Reviews", products.getReviews());
			list.add(tempMap);
			 
			 map.put("ProductData", list);
			 map.put("message", "success");
	         map.put("status", true);

	    } catch (Exception e) {
	        map.put("records", list);
	        map.put("message", "Error retrieving product data");
	        map.put("status", false);
	    }
	 
	 return map;
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



}

	
	
	