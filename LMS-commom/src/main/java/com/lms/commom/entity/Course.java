/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.commom.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String description;
    private Boolean status;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course", cascade = CascadeType.ALL)
    private List<Chapter> chapters = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course", cascade = CascadeType.ALL)
    private List<Exam> exams = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courseId", cascade = CascadeType.ALL)
    private List<CourseMember> courseMembers = new ArrayList<>();
}
