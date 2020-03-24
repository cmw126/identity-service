package com.identity.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.identity.metric.MetricsService;
import com.identity.model.User;
import com.identity.model.UserDetail;
import com.identity.model.UserRequest;
import com.identity.repository.UserDetailRepository;
import com.identity.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserDetailRepository userDetailRepo;
	
	
	// create user 
	public UserRequest createRecord(UserRequest req) {
		User returnUser = null;
		long start = System.currentTimeMillis(); 
		ModelMapper requestMapper = new ModelMapper();

		User user = requestMapper.map(req, User.class);
		UserDetail userDetail = requestMapper.map(req, UserDetail.class);
		returnUser = userRepo.findByUserName(user.getUserName());
        if (null != returnUser) {
//            ErrorDetails errorDetails = new ErrorDetails(API_ERROR_ALREADY_EXITS_ERROR, ACTIVE_USER);
//        	ErrorDetails errorDetails = new ErrorDetails(API_ERROR_ALREADY_EXITS_ERROR, ACTVIE_USER);
//            UserException ex = new UserException(errorDetails);
//            throw ex;
        }
		user.setUserName(req.getUserName());
		user.setType(req.getType());
		user.setAccessLevel(req.getAccessLevel());
		
		userDetail.setEmail(req.getEmail());
		userDetail.setFullName(req.getFullName());
		userDetail.setPhone(req.getPhone());
		userDetail.setUser(user);
		
		userDetailRepo.save(userDetail);
		userRepo.save(user);
//        MetricsService.observeTime(TIMER_NAME, System.currentTimeMillis() - start, METHOD, "createClaim");
//        MetricsService.incrementCounter(COUNTER_NAME, METHOD, "createClaim");
		
		return req;
	}
}
