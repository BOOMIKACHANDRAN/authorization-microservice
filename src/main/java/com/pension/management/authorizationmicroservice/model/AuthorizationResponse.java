package com.pension.management.authorizationmicroservice.model;

public class AuthorizationResponse {

	private final String token;
	
	public AuthorizationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}