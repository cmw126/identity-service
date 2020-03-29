package com.identity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.identity.common.utils.JwtUtil;
import com.identity.common.utils.ResponseBuilder;
import com.identity.common.utils.ResponseObject;
import com.identity.dto.UserRequest;
import com.identity.dto.UserResponse;
import com.identity.model.AuthenticationRequest;
import com.identity.model.AuthenticationResponse;
import com.identity.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ResponseBuilder responseBuilder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwTokenUtil;
	
	
	@PostMapping("/")
	public ResponseEntity<ResponseObject> insertUser(@RequestBody UserRequest request) {
		
		UserResponse userResponse = userService.createRecord(request);
		
		return responseBuilder.buildAPISuccessResponse(userResponse, userResponse.getMessage());
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> readUsers() {
		
		List<UserResponse> userResponse = userService.readRecords();
		
		return responseBuilder.buildAPISuccessResponse(userResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> readUserTest(@PathVariable("id") int userId) {
		
		UserResponse userResponse = userService.searchRecord(userId);
		
		return responseBuilder.buildAPISuccessResponse(userResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateLevel(@PathVariable("id") int userId, @RequestBody UserRequest request) {
		
		UserResponse userResponse = userService.updateRecord(userId, request);
		
		return responseBuilder.buildAPISuccessResponse(userResponse);
	}
	
	@PutMapping("/revoke/{id}")
	public ResponseEntity<ResponseObject> revokeUser(@PathVariable("id") int userId) {
		
		String userResponse = userService.revokeRecord(userId);
		
		return responseBuilder.buildAPISuccessResponse(userResponse);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//					new UsernamePasswordAuthenticationToken("paul", "admn1234")
					);
		}catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
