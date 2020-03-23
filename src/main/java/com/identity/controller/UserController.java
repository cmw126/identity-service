package com.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identity.model.UserRequest;
import com.identity.service.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserController {
	
	@Autowired
	UserService us;
	
	@PostMapping("/add")
	public String insertUser(@RequestBody UserRequest req) {
		
//		UserService us = new UserService();
		us.saveRecord(req);
		return "inserted";
	}
}
