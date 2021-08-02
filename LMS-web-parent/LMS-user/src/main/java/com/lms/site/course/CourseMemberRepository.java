/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.commom.entity.CourseMember;
import com.lms.commom.entity.CourseMemberKey;
import java.util.List;

/**
 *
 * @author Admin
 */

@Repository
public interface CourseMemberRepository extends JpaRepository<CourseMember, CourseMemberKey>{
    
    List<CourseMember> findByUserId(int userId);

    
}
