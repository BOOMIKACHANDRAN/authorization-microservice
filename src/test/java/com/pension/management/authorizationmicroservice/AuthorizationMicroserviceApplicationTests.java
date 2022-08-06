package com.pension.management.authorizationmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pension.management.authorizationmicroservice.configuration.service.UsersDetailsService;
import com.pension.management.authorizationmicroservice.util.JwtUtil;

@SpringBootTest
class AuthorizationMicroserviceApplicationTests {
	
	@Autowired
	private UsersDetailsService usersDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testLoadUserByUsernamePresent() {
		UserDetails userDetails = usersDetailsService.loadUserByUsername("boomika");
		assertNotNull(userDetails);
	}
	
	@Test
	public void testLoadUserByUsernameNotPresent() {
		boolean valid = false;
		try {
			UserDetails userDetails = usersDetailsService.loadUserByUsername("kowsi");
		}
		catch(UsernameNotFoundException e) {
			valid = true;
		}
		assertTrue(valid);
	}
	
	@Test
	public void testGenerateToken() {
		UserDetails userDetails = usersDetailsService.loadUserByUsername("boomika");
		String token = jwtUtil.generateToken(userDetails);
		assertNotNull(token);
	}
	

}
