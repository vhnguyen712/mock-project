/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.resource;

import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Service
@Transactional
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    public void saveResources (Chapter chapter, Resources resources){
        resources.setChapter(chapter);
        resourceRepository.save(resources);
    }
}
