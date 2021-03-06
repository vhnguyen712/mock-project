package com.lms.commom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Resources {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
        @EqualsAndHashCode.Include
	private int id;
	
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "chapter_id")
	private Chapter chapter;
}
