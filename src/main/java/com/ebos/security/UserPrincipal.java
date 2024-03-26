package com.ebos.security;



import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ebos.tables.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

		private Long id;
	    private String firstname;
	    private String username;
	    private String email;
	    private String password;
	    private Collection<? extends GrantedAuthority> authorities;
	    private Long mobileNo;

	public UserPrincipal(Long id, String firstname, String username, String email, String password, Long mobileNo,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstname = firstname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobileNo=mobileNo;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserPrincipal(user.getId(), user.getFirstname(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getMobileNo(),authorities);
	}

	public Long getId() {
		return id;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public String getName() {
		return firstname;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	public String getFirstname() {
		return firstname;
	}
	
	
	

}
