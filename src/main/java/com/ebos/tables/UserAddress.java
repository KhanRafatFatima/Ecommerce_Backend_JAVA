package com.ebos.tables;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;

@Entity
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 6, fraction = 0, message = "Pincode should be of 6 digits")
    private Long pincode;

    private String streetAddress;

    private String landMark;

    private String city;   

    private String state;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserAddress() {
        // Default constructor
    }

    public UserAddress(Long pincode, String streetAddress, String landMark, String city, String state, User user) {
        this.pincode = pincode;
        this.streetAddress = streetAddress;
        this.landMark = landMark;
        this.city = city;
        this.state = state;
        this.user = user;
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
}