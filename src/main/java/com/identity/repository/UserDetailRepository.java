package com.identity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.identity.dto.UserResponse;
import com.identity.model.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>{

	UserDetail findByUserId(int userId);
	
}
