package com.example.jwtsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.repository.UserRepository;
import com.example.jwtsecurity.util.JwtUtil;
@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	  @Autowired
	    private JwtUtil jwtUtil;
	  
	  @Override
		public String authenticate(String username, String password) {
		  User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        if (!user.getPassword().equals(password)) {
	            throw new RuntimeException("Invalid password");
	        }

	        return jwtUtil.generateToken(user.getUsername(), user.getRole());
	
	}

}
