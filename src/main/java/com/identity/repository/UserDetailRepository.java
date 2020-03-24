package com.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.identity.model.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>{

}
