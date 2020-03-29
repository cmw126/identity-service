package com.identity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.identity.dto.UserResponse;
import com.identity.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUserName(String userName);
	
	User findById(int userId);
	
	@Query("SELECT new com.identity.dto.UserResponse(a.userName, a.type, a.accessLevel, b.fullName, b.email, b.phone,\n" + 
			"			a.status ) from User a left join UserDetail b on a.id = b.user where a.id=?1")
	public UserResponse getJoinUser(int id);
	
	@Query("SELECT new com.identity.dto.UserResponse(a.userName, a.type, a.accessLevel, b.fullName, b.email, b.phone, a.status) from User a inner join UserDetail b on a.id=b.user where a.status = 'Active'")
	public List<UserResponse> getJoinUsers();

	// example to update using @Query but only allow to update single table and allow to use int/void method
	@Modifying
	@Transactional
//	@Query(value = "UPDATE new com.identity.dto.UserResponse(a.accessLevel) User a INNER JOIN User_Detail b on a.user_id = b.user_id SET a.access_level = :level where a.user_id = :id")
	@Query(value = "UPDATE User set accessLevel= :level where user_id = :id")
	public int updateJoinUser(@Param("level") int level, @Param("id") int id);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE User set status='Inactive' where user_id = ?1")
	public int revokeRecord(int userId);
	
	// Authenticate
//	public Boolean findByUserNameAndPassword(String userName, String password);
	
}
