package com.lms.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.commom.entity.User;

@Service
@Transactional
public class UserService {

	private static final int USER_PER_PAGE = 3;
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getListUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	public Page<User> listByPage(int pageNum, String keyword) { 
		Pageable pageable = PageRequest.of(pageNum - 1, USER_PER_PAGE);
		
		if (keyword != null) {
			return userRepository.findAll(keyword, pageable);
		}
		
		return userRepository.findAll(pageable);
	}
	
	public void updateEnable(Integer id, boolean enable) {
		userRepository.enableUser(id, enable);
	}
}
