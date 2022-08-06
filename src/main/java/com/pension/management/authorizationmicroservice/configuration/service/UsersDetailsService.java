package com.pension.management.authorizationmicroservice.configuration.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pension.management.authorizationmicroservice.model.UserDetail;
import com.pension.management.authorizationmicroservice.repository.UserDetailRepository;

@Service
public class UsersDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetail user = userDetailRepository.findById(username).orElse(null);
		if (user != null) {
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Username Not Found");
		}
	}

}
