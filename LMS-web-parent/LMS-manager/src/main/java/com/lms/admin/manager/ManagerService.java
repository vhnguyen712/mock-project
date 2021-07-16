package com.lms.admin.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.commom.entity.Manager;

@Service
@Transactional
public class ManagerService {

	private static final int MANAGER_PER_PAGE = 3;
	@Autowired
	ManagerRepository managerRepository;
	
	public List<Manager> getListUsers() {
		return (List<Manager>) managerRepository.findAll();
	}
	
	public Page<Manager> listByPage(int pageNum, String keyword) { 
		Pageable pageable = PageRequest.of(pageNum - 1, MANAGER_PER_PAGE);
		
		if (keyword != null) {
			return managerRepository.findAll(keyword, pageable);
		}
		
		return managerRepository.findAll(pageable);
	}
	
	public void updateEnable(Integer id, boolean enable) {
		managerRepository.enableManager(id, enable);
	}
	
	public void delete(int id) {
		managerRepository.deleteById(id);
	}
}
