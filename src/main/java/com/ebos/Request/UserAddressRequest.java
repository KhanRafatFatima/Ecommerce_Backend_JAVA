package com.ebos.Request;

import jakarta.validation.constraints.Digits;

public class UserAddressRequest {

   @Digits(integer = 6, fraction = 0, message = "Pincode should be of 6 digits")
    private Long pincode;

    private String streetAddress;

    private String landMark;

    private String city;

    private String state;

    public UserAddressRequest() {
        // Default constructor
    }

    public UserAddressRequest(Long pincode, String streetAddress, String landMark, String city, String state) {
        this.pincode = pincode;
        this.streetAddress = streetAddress;
        this.landMark = landMark;
        this.city = city;
        this.state = state;
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
}
