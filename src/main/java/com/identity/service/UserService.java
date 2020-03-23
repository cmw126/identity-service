package com.identity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.identity.model.User;
import com.identity.model.UserDetail;
import com.identity.model.UserRequest;
import com.identity.model.UserResponse;
import com.identity.repository.UserDetailRepository;
import com.identity.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserDetailRepository userDetailRepo;
	
	public UserResponse saveRecord(UserRequest req) {
		
		
		User user = new User();
		UserDetail userDetail = new UserDetail();
		user.setUserName(req.getUserName());
		user.setType(req.getType());
		user.setAccessLevel(req.getAccessLevel());
		
		userDetail.setEmail(req.getEmail());
		userDetail.setFullName(req.getFullName());
		userDetail.setPhone(req.getPhone());
		userDetail.setUser(user);
		
		userDetailRepo.save(userDetail);
		userRepo.save(user);
		
		
		return user;
	}
}
