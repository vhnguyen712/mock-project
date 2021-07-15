package com.lms.admin.manager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lms.commom.entity.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

	Role findByName(String name);

}
