/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.resource;

import com.lms.commom.entity.Resources;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resources, Integer> {

    public List<Resources> findByChapter_Id(int Id);
    
}
