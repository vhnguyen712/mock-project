package com.lms.admin.resource;

import org.springframework.data.repository.CrudRepository;

import com.lms.commom.entity.Resources;

public interface ResourceRepository extends CrudRepository<Resources, Integer> {

}
