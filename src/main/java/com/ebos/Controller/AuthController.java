 package com.ebos.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebos.Request.LoginRequest;
import com.ebos.Request.SignUpRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.JwtAuthenticationResponse;
import com.ebos.exception.AppException;
import com.ebos.repository.RoleRepository;
import com.ebos.repository.UserRepository;
import com.ebos.security.JwtTokenProvider;
import com.ebos.tables.Role;
import com.ebos.tables.RoleName;
import com.ebos.tables.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse("false", "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse("false", "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        if(userRepository.existsByMobileNo(signUpRequest.getMobileNo())) {
            return new ResponseEntity(new ApiResponse("false", "Mobile Number already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(
            signUpRequest.getFirstname(), 
            signUpRequest.getMiddlename(), 
            signUpRequest.getLastname(),   
            signUpRequest.getUsername(),   
            signUpRequest.getMobileNo(),   
            signUpRequest.getEmail(),     
            signUpRequest.getPassword(),   
            LocalDateTime.now(),  
            signUpRequest.getIntro(),      
            signUpRequest.getProfile()     
        );


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.BUYER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

      

        return new ResponseEntity<ApiResponse>(new ApiResponse("true", "User registered successfully"),HttpStatus.OK);
    }
}

