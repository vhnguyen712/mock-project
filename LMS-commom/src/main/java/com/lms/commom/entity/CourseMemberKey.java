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
    
    private int courseId;
    private int userId;

    public CourseMemberKey() {
    }

    public CourseMemberKey(int courseId, int userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.courseId;
        hash = 41 * hash + this.userId;
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
        if (this.courseId != other.courseId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }
}
