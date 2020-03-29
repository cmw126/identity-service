package com.identity.services;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.identity.common.constants.UserConstants;
import com.identity.dto.UserRequest;
import com.identity.dto.UserResponse;
import com.identity.metric.MetricsService;
import com.identity.model.User;
import com.identity.model.UserDetail;
import com.identity.model.UserObjectMapperImp;
import com.identity.repository.UserDetailRepository;
import com.identity.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserDetailRepository userDetailRepo;
	
	
	// create user 
	public UserResponse createRecord(UserRequest req) {
		User returnUser = null;
		long start = System.currentTimeMillis(); 
		User dup = userRepo.findByUserName(req.getUserName());
		if(null != dup) {
			UserResponse ur = new UserResponse();
			ur.setMessage(UserConstants.RESPONSE_DUPLICATE);
			return ur;
		}
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
		user.setStatus(UserConstants.ACTIVE);
		user.setMessage(UserConstants.RESPONSE_INSERTED);
		
		userDetail.setEmail(req.getEmail());
		userDetail.setFullName(req.getFullName());
		userDetail.setPhone(req.getPhone());
		userDetail.setUser(user);
		userDetailRepo.save(userDetail);
		userRepo.save(user);
//        MetricsService.observeTime(TIMER_NAME, System.currentTimeMillis() - start, METHOD, "createClaim");
//        MetricsService.incrementCounter(COUNTER_NAME, METHOD, "createClaim");
		UserObjectMapperImp mapper = new UserObjectMapperImp();
		return mapper.userAndUserDetailMap(user, userDetail);
	}
	
	// read records
	public List<UserResponse> readRecords() {

		return userRepo.getJoinUsers();
	}
	
	// read record
	public UserResponse searchRecord(int id) {
		
		return userRepo.getJoinUser(id);
		
	}
	
	// update record
	public UserResponse updateRecord(int userId, UserRequest req) {
		
		User user = userRepo.findById(userId);
		UserDetail userDetail = userDetailRepo.findByUserId(userId);
		
		user.setAccessLevel(req.getAccessLevel());
		user.setType(req.getType());
		userDetail.setEmail(req.getEmail());
		userDetail.setFullName(req.getFullName());
		userDetail.setPhone(req.getPhone());
		userRepo.save(user);
		userDetailRepo.save(userDetail);
		UserObjectMapperImp mapper = new UserObjectMapperImp();
		
		return mapper.userAndUserDetailMap(user, userDetail);
		
	}
	
	// delete record
	public String revokeRecord(int userId) {
		String rStatus;
		int status = userRepo.revokeRecord(userId);
		if (1==status) {
			rStatus = UserConstants.RESPONSE_DELETED;
		} else {
			rStatus = "Unable to revoke user";
		}
		
		return rStatus;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = userRepo.findByUserName(userName);
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

}
