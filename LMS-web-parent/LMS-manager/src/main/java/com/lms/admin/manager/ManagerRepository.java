package com.lms.admin.manager;

import org.springframework.data.repository.CrudRepository;

import com.lms.commom.entity.Manager;

public interface ManagerRepository extends CrudRepository<Manager, Integer>{

	Manager getManagerByEmail(String email);

}
