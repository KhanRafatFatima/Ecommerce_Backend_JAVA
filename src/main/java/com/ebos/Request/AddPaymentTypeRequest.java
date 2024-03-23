package com.ebos.Request;

public class AddPaymentTypeRequest {
	
	private String paymentType;
	
	public AddPaymentTypeRequest() {
		
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public AddPaymentTypeRequest(String paymentType) {
		super();
		this.paymentType = paymentType;
	}
	
	

}
