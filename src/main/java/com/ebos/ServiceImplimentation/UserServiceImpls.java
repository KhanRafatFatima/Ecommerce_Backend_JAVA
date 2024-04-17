package com.ebos.ServiceImplimentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebos.Request.SignUpRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.GetUserDataResponse;
import com.ebos.Response.SetListResponse;
import com.ebos.Response.UpdateResponse;
import com.ebos.Service.UserService;
import com.ebos.repository.UserRepository;
import com.ebos.security.UserPrincipal;
import com.ebos.tables.User;

@Service
public class UserServiceImpls implements UserService {

	@Autowired
	private UserRepository userRepository;

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
	 public Map<String,Object> deleteUserAccount() {
		 Map<String,Object> map=new HashMap<>();
		
		try {
			UserPrincipal  authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());
			if(userOptional.isPresent()) {
			User user = userOptional.get();
			
			userRepository.delete(user);
			
			map.put("status", true);
			map.put("message", "Deleted Your Account Successfully");
			}else {
				map.put("status", false);
				map.put("message", "user not found");
			}
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "error occured");
		}
		return map;

	}


	@Override
	public Map<String, Object> updateUserDetails(SignUpRequest updateRequest) {
         Map<String,Object> map=new HashMap<>();
		
		try {
			UserPrincipal  authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());
			
			if(userOptional.isPresent()) {
				User user = userOptional.get();
				
				user.setFirstname(updateRequest.getFirstname());
				user.setLastname(updateRequest.getLastname());
				user.setMiddlename(updateRequest.getMiddlename());
				user.setEmail(updateRequest.getEmail());
				user.setMobileNo(updateRequest.getMobileNo());
				user.setPassword(updateRequest.getPassword());
				user.setUsername(updateRequest.getUsername());
				user.setProfile(updateRequest.getProfile());
				user.setIntro(updateRequest.getIntro());
				
				userRepository.save(user);
				
				map.put("status", true);
				map.put("message", "Updated the data successfully");
				
				
			}else {
				map.put("status", false);
				map.put("message", "user not found");
			}
			
			
		}catch(Exception e) {
			map.put("status", false);
			map.put("message", "error occured");
		}
		
		return map;
	}
	
	
	

	
}
	