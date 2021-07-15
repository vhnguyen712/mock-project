package com.lms.commom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[user]")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String email;
	private String pass;
	private String name;
	private String phone;
	private boolean status;
	
	@Column(name = "verification_code", length = 64)
	private String verificationCode;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "authentication_type", length = 10)
	private AuthenticationType authenticationType;
	
	@Column(name = "account_non_locked")
	private boolean accountNonLocked;
	
	@Column(name = "failed_attempt")
	private int failedAttempt;
	
	@Column(name = "lock_time")
	private Date lockTime;
	
	public User(int id, String email, String pass, String name, String phone, Boolean status) {
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
		this.status = status;
	}
	
}
