/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.commom.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Admin
 */

@Embeddable
public class CourseMemberKey implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int course_id;
    private int user_id;

    public CourseMemberKey() {
    }

    public CourseMemberKey(int course_id, int user_id) {
        this.course_id = course_id;
        this.user_id = user_id;
    }
    
    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.course_id;
        hash = 11 * hash + this.user_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CourseMemberKey other = (CourseMemberKey) obj;
        if (this.course_id != other.course_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }
    
    
}
