package com.ebos.tables;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 40)
	private String name;

	@NotBlank
	@Size(max = 15)
	private String username;

	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;
	
	private LocalDate registerDate;
	
	@NotNull
	@Digits(integer = 10, fraction = 0, message = "Mobile number must be 10 digits")
	private Long mobileNo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_order",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="order_id"))
	private Set<Order> orders=new HashSet<>();
	
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address_Details address_Details;
	
	@OneToOne(mappedBy ="user",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Cart cart;
	
     public User() {

    }

    public User(String name, String username, String email, String password, Long mobileNo) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
        this.registerDate = LocalDate.now();  // Set the current date
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Address_Details getAddress_Details() {
		return address_Details;
	}

	public void setAddress_Details(Address_Details address_Details) {
		this.address_Details = address_Details;
	}
	
	
	
	
}
