/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.commom.entity.CourseMember;
import java.util.List;

/**
 *
 * @author Admin
 */

@Service
@Transactional
public class CourseMemberService {
    
    @Autowired
    CourseMemberRepository memberRepository;
    
    public void attend(CourseMember member) {
        memberRepository.save(member);
    }
    
    public List<CourseMember> getMyCourse(int id) {
        return memberRepository.findByUserId(id);
    }
    
}
