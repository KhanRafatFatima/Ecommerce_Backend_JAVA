package com.ebos.ServiceImplimentation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebos.Request.SignUpRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteResponse;
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
	public SetListResponse findAll() {
		SetListResponse setListResponse = new SetListResponse();
		List<User> list = userRepository.findAll();
		setListResponse.setMessage("Success");
		setListResponse.setStatus("True");
		setListResponse.setList(list);
		
		return setListResponse;
	}

//      public GetUserDataResponse getUserData() {	
//		GetUserDataResponse  userResponse = new GetUserDataResponse();
//		try {
//			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//			Optional<User> user2 = userRepository.findById(user.getId());
//			
//			User user3 = user2.get();
//			userResponse.setUser(user3);
//			userResponse.setStatus(true);
//			userResponse.setMessage("success");
//			
//			return userResponse;
//			
//		}catch (Exception e) {
//			return userResponse;
//		}

	

	
	public DeleteResponse deleteUser() {
		DeleteResponse deleteResponse=new DeleteResponse();
		
		try {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Optional<User> user2 = userRepository.findById(user.getId());
			
			User user3 = user2.get();
			
			userRepository.delete(user3);
			
			deleteResponse.setMessage("success");
			deleteResponse.setStatus("true");
			return deleteResponse;
		} catch (Exception e) {
			deleteResponse.setMessage("Failed to update");
			deleteResponse.setStatus("false");
			return deleteResponse;
		}

	}
	@Override
	public UpdateResponse updateUser(SignUpRequest signUpRequest) {
		UpdateResponse userInfoResponse = new UpdateResponse();
		try {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Optional<User> user2 = userRepository.findById(user.getId());
			
			User user3 = user2.get();
			
			user3.setEmail(signUpRequest.getEmail());
			user3.setName(signUpRequest.getName());
			user3.setUsername(signUpRequest.getUsername());
			user3.setPassword(signUpRequest.getPassword());
			
			userRepository.save(user3);
			
			userInfoResponse.setMessage("success");
			userInfoResponse.setSuccess(true);
			return userInfoResponse;
		} catch (Exception e) {
			userInfoResponse.setMessage("Failed to update");
			userInfoResponse.setSuccess(false);
			return userInfoResponse;
		}
	}

	
}
	