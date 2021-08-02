/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.member;

import com.lms.commom.entity.CourseMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface MemberRepository extends PagingAndSortingRepository<CourseMember, Integer>{
    @Query("Update CourseMember c Set c.status = ?2 Where c.userId = ?1")
    @Modifying
    public void banMember(Integer id,boolean enable);
    @Query("Select c From CourseMember c Where c.status = true")
    public Page<CourseMember> findByActiveTrue(String keyword,Pageable pageable);
    
    
}
