package com.identity.model;

import lombok.Data;

@Data
public class UserRequest {

	// user response columns
	private String userName;
	private String type;
	private String accessLevel;
	private String fullName;
	private String email;
	private String phone;
	
}
