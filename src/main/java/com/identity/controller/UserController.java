package com.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identity.common.utils.ResponseBuilder;
import com.identity.common.utils.ResponseObject;
import com.identity.model.UserRequest;
import com.identity.services.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ResponseBuilder responseBuilder;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseObject> insertUser(@RequestBody UserRequest req) {
		
		UserRequest ur = userService.createRecord(req);
		
		return responseBuilder.buildAPISuccessResponse(ur);
	}
}
