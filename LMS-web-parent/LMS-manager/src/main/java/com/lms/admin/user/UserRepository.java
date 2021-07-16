package com.lms.admin.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lms.commom.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

	@Query("Update User u Set u.status = ?2 Where u.id = ?1")
	@Modifying
	public void enableUser(Integer id,boolean enable);
}
