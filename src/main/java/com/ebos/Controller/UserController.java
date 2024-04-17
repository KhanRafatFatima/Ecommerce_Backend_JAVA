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
import com.ebos.Request.SignUpRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.GetUserDataResponse;
import com.ebos.Response.SetListResponse;
import com.ebos.Response.UpdateResponse;
import com.ebos.Service.UserService;
import com.ebos.tables.User;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getUserData")
	public ResponseEntity<Map<String,Object>> getUserData(){
       Map<String,Object> response=userService.getUserData();
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/deleteUserAccount")
	public ResponseEntity<Map<String,Object>> deleteAccount(){
		Map<String,Object> response=userService.deleteUserAccount();
		
		return ResponseEntity.ok(response);
	}

    @PutMapping("/updateUserAccount")
    public ResponseEntity<Map<String,Object>> updateAccount(@RequestBody SignUpRequest updateRequest){
		Map<String,Object> response=userService.updateUserDetails(updateRequest);
		
		return ResponseEntity.ok(response);
	}
	
}


