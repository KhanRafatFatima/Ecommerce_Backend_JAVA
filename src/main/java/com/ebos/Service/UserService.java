package com.ebos.Service;
import java.util.Map;

import com.ebos.Request.SignUpRequest;

public interface UserService {
	 	
	  public Map<String,Object> deleteUserAccount();
	    
	  public Map<String,Object> getUserData();
	  
	  public Map<String,Object> updateUserDetails(SignUpRequest updateRequest);
	    

}
