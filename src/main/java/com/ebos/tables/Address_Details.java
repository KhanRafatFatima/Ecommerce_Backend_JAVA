package com.ebos.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Address_Details {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=6,max=6,message="Pincode should be of 6 digits")
	private Long pincode;
	
	private String streetAddress;
	
	private String landMark;
	
	private String city;
	
	private String state;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	public Address_Details() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address_Details(@Size(min = 6, max = 6, message = "Pincode should be of 6 digits") Long pincode,
			String streetAddress, String landMark, String city, String state, User user) {
		super();
		this.pincode = pincode;
		this.streetAddress = streetAddress;
		this.landMark = landMark;
		this.city = city;
		this.state = state;
		this.user = user;
	}
	
	
	
}
