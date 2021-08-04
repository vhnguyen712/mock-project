/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.member;

import com.lms.commom.entity.CourseMember;
import org.springframework.data.domain.Page;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class MemberService {
    private static final int MEMBER_PAGE = 4;
    @Autowired
    MemberRepository memberRepository;
    public List<CourseMember> getListMembers(){
        return (List<CourseMember>) memberRepository.findAll();
    }
    public Page<CourseMember> listByPagee(int pageNum,String keyword){
        Pageable pageable = PageRequest.of(pageNum - 1,MEMBER_PAGE);
        if(keyword != null){
            return memberRepository.findByActiveTrue(keyword, pageable);
        }
        return memberRepository.findAll(pageable);
    }
    public void update(Integer id,boolean enable){
        memberRepository.banMember(id,enable);
    }
    
    
}   
