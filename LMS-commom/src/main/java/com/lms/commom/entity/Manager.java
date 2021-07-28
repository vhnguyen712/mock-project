package com.lms.commom.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manager")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
        @EqualsAndHashCode.Include
	private int id;
	
	private String email;
	private String password;
	private String name;
	private Date createDate;	
	private boolean status;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "manager_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles = new HashSet<>();
	
	public void addRole(Role role) {
		this.roles.add(role);
	}

	public Manager(String email, String password, String name, Date createDate, boolean status) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.createDate = createDate;
		this.status = status;
	}
        
        @OneToMany
        @JoinColumn (name = "manager_id")
	private Set<Course> courses;
	
}
