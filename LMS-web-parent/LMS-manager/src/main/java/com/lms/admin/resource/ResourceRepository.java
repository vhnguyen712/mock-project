package com.lms.admin.resource;

import org.springframework.data.repository.CrudRepository;

import com.lms.commom.entity.Resources;
import java.util.List;

public interface ResourceRepository extends CrudRepository<Resources, Integer> {

    public List<Resources> findByChapter_Id(int Id);
    
}
