package com.identity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {

	// user response attributes
	private String userName;
	private String type;
	private int accessLevel;
	private String fullName;
	private String email;
	private String phone;
	private String status;
	private String message;
	
	public UserResponse(String userName, String type, int accessLevel, String fullName, String email, String phone,
			String status) {
		this.userName = userName;
		this.type = type;
		this.accessLevel = accessLevel;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}

	public UserResponse(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	
	
	
	
	
}
