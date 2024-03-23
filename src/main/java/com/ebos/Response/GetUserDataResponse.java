package com.ebos.Response;

import com.ebos.tables.User;

public class GetUserDataResponse {
	
	  private Boolean status;
	  private String message;
	  private User user;
	
	 
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	  

}
