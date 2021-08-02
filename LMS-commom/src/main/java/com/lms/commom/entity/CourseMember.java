/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.commom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "course_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CourseMemberKey.class)
public class CourseMember implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "course_id")
    private int courseId;
    
    @Id
    @Column(name = "user_id")
    private int userId;
    
    private boolean status;

    
    public boolean isStatus() {
        return status;
    }
    
}
