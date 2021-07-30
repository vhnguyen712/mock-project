/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.resource;

import com.lms.commom.entity.Resources;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class ResourceService {
    
    @Autowired
    ResourceRepository repository;
    
    public List<Resources> getResourceByChapterId(int Id) {
        return repository.findByChapter_Id(Id);
    }
    
}
