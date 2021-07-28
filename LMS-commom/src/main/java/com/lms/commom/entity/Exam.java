package com.lms.commom.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
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
import lombok.ToString;

@Entity
@Table(name = "exam")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int duration;
	
	private String available;
	
	private String due;
	
	@Column(name = "create_date")
	private Date createDate;
        
        
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;
	
        
        @ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Manager manager;
        
        @ToString.Exclude
        @OneToMany(fetch = FetchType.LAZY,mappedBy = "exam", cascade = CascadeType.ALL)
        private List<Question> question = new ArrayList<>();

}
