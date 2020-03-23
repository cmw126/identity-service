package com.identity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="user_name")
	private String userName;
	
	private String type;
	
	@Column(name="access_level")
	private String accessLevel;
	
//	@OneToOne(mappedBy="user")
//	private UserDetail userDetail;
}
