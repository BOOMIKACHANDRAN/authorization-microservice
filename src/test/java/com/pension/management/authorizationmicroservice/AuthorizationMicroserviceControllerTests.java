package com.pension.management.authorizationmicroservice;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.pension.management.authorizationmicroservice.configuration.service.UsersDetailsService;
import com.pension.management.authorizationmicroservice.controller.AuthorizationController;
import com.pension.management.authorizationmicroservice.model.UserDetail;
import com.pension.management.authorizationmicroservice.util.JwtUtil;

@AutoConfigureMockMvc
@SpringBootTest
public class AuthorizationMicroserviceControllerTests {

	@Mock
	private UsersDetailsService usersDetailsService;

	@Mock
	private JwtUtil jwtUtil;

	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	AuthorizationController authorizationController;

	@Test
	void authenticateTest() throws Exception {
		String jsonToken = "{\n" + "\"username\": \"Boomika\",\n" + "\"password\": \"boomi@16\"\n" + "}";
		UserDetails userDetails = null;
		String token = null;

		UserDetail userDetail = new UserDetail("Boomika", "Boomi@16");
		Mockito.when(usersDetailsService.loadUserByUsername(userDetail.getUsername())).thenReturn(userDetails);
		Mockito.when(jwtUtil.generateToken(userDetails)).thenReturn(token);

		mockMvc.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonToken)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void validateTest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCb29taWthIiwiZXhwIjoxNjYwOTAyMjE5LCJpYXQiOjE2NjA5MDA0MTl9.SNGHWNLeCRxwPvT6fjIT2r6Foc9ovRUdbmfp6oxxqCE";

		Mockito.when(jwtUtil.validateToken(token.substring(7))).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/validate-token").header("Authorization", "Bearer " + token))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

}
