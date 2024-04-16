package com.ebos.ServiceImplimentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebos.Service.AdminService;
import com.ebos.repository.UserRepository;
import com.ebos.security.UserPrincipal;
import com.ebos.tables.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserRepository userRepository;
	@Override
	public Map<String, Object> getAllUsers() {
		 Map<String, Object> map=new HashMap<>();
		
		 try {
			 UserPrincipal authenticatedUser=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 
			 Optional<User> userOptional=userRepository.findById(authenticatedUser.getId());
			 
			 if(userOptional.isPresent()) {
				 List<Map<String,Object>> listMap=new ArrayList<>();
				 
				 List<User> userList=userRepository.findAll();
				
				 
				 for(User list:userList) {
					 Map<String,Object> tempMap=new HashMap<>();
					 
					 tempMap.put("firstname", list.getFirstname());
					 tempMap.put("middlename", list.getMiddlename());
					 tempMap.put("lastname", list.getLastname());
					 tempMap.put("username", list.getUsername());
					 tempMap.put("mobileNo", list.getMobileNo());
					 tempMap.put("Email", list.getEmail());
					 if (list.getIntro() != null && !list.getIntro().isEmpty() && list.getProfile() != null && !list.getProfile().isEmpty()) {
						    tempMap.put("Seller Intro", list.getIntro());
						    tempMap.put("Seller Profile", list.getProfile());
						}

					 
					 listMap.add(tempMap);
					 
					 map.put("data", listMap);
					 map.put("status", true);
					 map.put("message", "Successfully fetched");
					 
				 }
				 
				 
			 }else {
				 map.put("status", false);
				 map.put("message", "User not found");
			 }
			 
			 
			 
		 }catch(Exception e) {
			 map.put("status", false);
			 map.put("message", "Error Ocuured" +e.getMessage());
			 
		 }
		 
		 return map;
		 }
	
	

}
