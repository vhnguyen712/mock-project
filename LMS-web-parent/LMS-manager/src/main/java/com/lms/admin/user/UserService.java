package com.lms.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.commom.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getListUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	public void updateEnable(Integer id, boolean enable) {
		userRepository.enableUser(id, enable);
	}
}
