package com.ebos.Response;

import java.util.List;

import com.ebos.tables.User;



public class SetListResponse {
	private String message;
	private String status;
	List<User> list;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	

}
