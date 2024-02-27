package com.ebos.Controller;

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
import com.ebos.Response.DeleteResponse;
import com.ebos.Response.GetUserResponse;
import com.ebos.Response.SetListResponse;
import com.ebos.Response.UpdateResponse;
import com.ebos.Service.UserService;
import com.ebos.tables.User;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("/get")
	public ResponseEntity<?> getUserData() {
		
		try {
			return new ResponseEntity<GetUserResponse>(userService.getUserData(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<GetUserResponse>(userService.getUserData(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateUserInfo")
	public ResponseEntity<?> updateUserInfo(@RequestBody SignUpRequest signUpRequest) {
		
		try {
			return new ResponseEntity<UpdateResponse>(userService.updateUser(signUpRequest), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UpdateResponse>(userService.updateUser(signUpRequest),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}


