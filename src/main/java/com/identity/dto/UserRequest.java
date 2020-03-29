package com.identity.dto;

import lombok.Data;

@Data
public class UserRequest {

	// user response attributes
	private String userName;
	private String password;
	private String type;
	private int accessLevel;
	private String fullName;
	private String email;
	private String phone;
}
