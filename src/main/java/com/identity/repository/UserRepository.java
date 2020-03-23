package com.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.identity.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
