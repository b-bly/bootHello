package com.brendt.bootHello.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brendt.bootHello.dao.RoleDao;
import com.brendt.bootHello.dao.UserDao;
import com.brendt.bootHello.model.Role;
import com.brendt.bootHello.model.User;
import com.brendt.bootHello.payload.ApiResponse;
import com.brendt.bootHello.payload.JwtAuthenticationResponse;
import com.brendt.bootHello.payload.LoginRequest;
import com.brendt.bootHello.payload.SignUpRequest;
import com.brendt.bootHello.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserDao userDao;

    @Autowired
     RoleDao roleDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    

    @Autowired
    JwtTokenProvider tokenProvider;
    

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        if(userDao.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userDao.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleDao.findByName("ROLE_USER")
                .orElseThrow(() -> new Exception("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userDao.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	System.out.println("Login request: " + loginRequest.getUsername());
    	
    	UsernamePasswordAuthenticationToken   authToken      =     new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
    	        System.out.println("authentication: " + authToken.toString());
    	        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        //return ResponseEntity.ok(new ApiResponse(true, "Login successful."));
    }
}
