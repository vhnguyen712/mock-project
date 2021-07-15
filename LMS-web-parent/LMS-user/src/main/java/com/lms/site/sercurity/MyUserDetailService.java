package com.lms.site.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lms.commom.entity.User;
import com.lms.site.user.UserRepository;

public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepo.getUserByEmail(email);

		if (user != null) {
			return new MyUserDetail(user);
		}
		throw new UsernameNotFoundException("Not found user");
	}
}
