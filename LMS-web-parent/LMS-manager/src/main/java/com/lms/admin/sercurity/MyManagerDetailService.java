package com.lms.admin.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lms.admin.manager.ManagerRepository;
import com.lms.commom.entity.Manager;

public class MyManagerDetailService implements UserDetailsService {

	@Autowired
	ManagerRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Manager manager = repo.getManagerByEmail(email);

		if (manager != null) {
			return new MyManagerDetail(manager);
		}
		throw new UsernameNotFoundException("Not found user");
	}
}
