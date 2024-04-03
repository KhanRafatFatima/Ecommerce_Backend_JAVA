package com.ebos.Response;

import java.util.List;

import com.ebos.tables.Products;
import com.ebos.tables.User;



public class SetListResponse {
	private String message;
	private String status;
	List<Products> list;
	List<User> list2;
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
	public List<Products> getList() {
		return list;
	}
	public void setList(List<Products> list) {
		this.list = list;
	}
	public List<User> getList2() {
		return list2;
	}
	public void setList2(List<User> list2) {
		this.list2 = list2;
	}
	
   
}
