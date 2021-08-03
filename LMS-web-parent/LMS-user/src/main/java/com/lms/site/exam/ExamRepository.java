/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.site.exam;

import com.lms.commom.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Admin
 */
@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {


    @Query("select e from Exam e where e.course.id = ?1 and e.available<=?2 and e.due>=?2")
    public List<Exam> getExamByCourseID(int id, String date);

}
