package com.example.jwtsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtsecurity.dto.AuthRequest;
import com.example.jwtsecurity.dto.AuthResponse;
import com.example.jwtsecurity.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/ping")
	public String ping() {
	    return "AUTH CONTROLLER WORKING";
	}
	@PostMapping(
	        value = "/login",
	        consumes = "application/json",
	        produces = "application/json"
	)
	public AuthResponse login(@RequestBody AuthRequest request) {

	    System.out.println("LOGIN HIT");
	    System.out.println("Username = " + request.getUsername());
	    System.out.println("Password = " + request.getPassword());

	    String token = userService.authenticate(
	            request.getUsername(),
	            request.getPassword());

	    return new AuthResponse(token);
	}

		
}
