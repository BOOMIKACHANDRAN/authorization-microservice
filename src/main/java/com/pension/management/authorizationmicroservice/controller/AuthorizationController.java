package com.pension.management.authorizationmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pension.management.authorizationmicroservice.configuration.service.UsersDetailsService;
import com.pension.management.authorizationmicroservice.model.AuthorizationResponse;
import com.pension.management.authorizationmicroservice.model.UserDetail;
import com.pension.management.authorizationmicroservice.util.JwtUtil;

@RestController
public class AuthorizationController {

	@Autowired
	private UsersDetailsService usersDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody UserDetail userDetail) {
		
		final UserDetails userDetails = usersDetailsService.loadUserByUsername(userDetail.getUsername());
	
		if (userDetails.getPassword().equals(userDetail.getPassword())) {
			final String token = jwtUtil.generateToken(userDetails);
			return new ResponseEntity<>(new AuthorizationResponse(token), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("/validate-token")
	public boolean validateToken(@RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		try {
			return jwtUtil.validateToken(jwt);
		} catch (Exception e) {
			return false;
		}
	}

}
