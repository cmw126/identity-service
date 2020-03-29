package com.identity.model;

import com.identity.dto.UserResponse;

public class UserObjectMapperImp {

	public UserResponse userAndUserDetailMap(User user, UserDetail userDetail) {
		
		UserResponse ur = new UserResponse();
		ur.setUserName(user.getUserName());
		ur.setType(user.getType());
		ur.setStatus(user.getStatus());
		ur.setMessage(user.getMessage());
		ur.setAccessLevel(user.getAccessLevel());
		ur.setFullName(userDetail.getFullName());
		ur.setEmail(userDetail.getEmail());
		ur.setPhone(userDetail.getPhone());
		
		return ur;
		
	}
}
