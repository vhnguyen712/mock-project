package com.lms.admin.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lms.commom.entity.Manager;

public interface ManagerRepository extends PagingAndSortingRepository<Manager, Integer>{

	Manager getManagerByEmail(String email);

	@Query("Select m From Manager m Where m.email LIKE %?1% or m.name LIKE %?1%")
	public Page<Manager> findAll(String keyword, Pageable pageable);
	
	@Query("Update Manager m Set m.status = ?2 Where m.id = ?1")
	@Modifying
	public void enableManager(Integer id,boolean enable);
}
