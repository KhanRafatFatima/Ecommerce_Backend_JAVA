package com.ebos.Response;

import com.ebos.tables.User;

public class GetUserResponse {
	
	  private Boolean success;
	  private String message;
	  private User user;
	
	  public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
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
