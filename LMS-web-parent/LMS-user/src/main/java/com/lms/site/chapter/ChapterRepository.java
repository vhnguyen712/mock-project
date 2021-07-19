/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.chapter;

import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    public List<Chapter> findByCourse_Id(int id);
    
}
