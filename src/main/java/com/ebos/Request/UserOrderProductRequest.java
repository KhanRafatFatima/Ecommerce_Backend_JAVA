package com.ebos.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;

public class UserOrderProductRequest {
	
	private Long productId;
	
	private Long paymentId;
	
//	private LocalDateTime paymentDate;
//
//	private Long amount;

	private String status;  
	
	private Long addressId;
	
	private Long paymentTypeId;
	
    public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	@Size(min = 6, max = 6, message = "Pincode should be of 6 digits")
    private Long pincode;

    private String streetAddress;

    private String landMark;

    private String city;

    private String state;
    
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}


	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

//	public LocalDateTime getPaymentDate() {
//		return paymentDate;
//	}
//
//	public void setPaymentDate(LocalDateTime paymentDate) {
//		this.paymentDate = paymentDate;
//	}
//
//	public Long getAmount() {
//		return amount;
//	}
//
//	public void setAmount(Long amount) {
//		this.amount = amount;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserOrderProductRequest(Long productId, Long paymentId, LocalDateTime paymentDate, Long amount,
			String status, Long addressId, Long paymentTypeId,
			@Size(min = 6, max = 6, message = "Pincode should be of 6 digits") Long pincode, String streetAddress,
			String landMark, String city, String state) {
		super();
		this.productId = productId;
		this.paymentId = paymentId;
//		this.paymentDate = paymentDate;
//		this.amount = amount;
		this.status = status;
		this.addressId = addressId;
		this.paymentTypeId = paymentTypeId;
		this.pincode = pincode;
		this.streetAddress = streetAddress;
		this.landMark = landMark;
		this.city = city;
		this.state = state;
	}

	
	

}
