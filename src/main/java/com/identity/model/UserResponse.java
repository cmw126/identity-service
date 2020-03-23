package com.identity.model;

import lombok.Data;

@Data
public class UserResponse {

	// user response columns
	private String userName;
	private String type;
	private String fullName;
	private String email;
	private String phone;
}
