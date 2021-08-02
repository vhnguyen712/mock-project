/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.exam;

import com.lms.admin.exam.*;
import com.lms.commom.entity.Exam;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LENOVO
 */
@Repository
public interface ExamRepository extends PagingAndSortingRepository<Exam, Integer> {

    @Query("Select e From Exam e Where course_id = ?1 ")
    List<Exam> listExamsOfTeacher(int id);

    @Query("Select e From Exam e Where e.name LIKE %?1%")
    public Page<Exam> findAll(String keyword, Pageable pageable);

    @Query("Select e From Exam e Where e.course.id = ?1 and e.name like %?2%")
    public Page<Exam> findByCourse_Id(int id, Pageable pageable, String keyword);

    @Query("Select e From Exam e Where e.course.id = ?1 ")
    public Page<Exam> findByCourse_Id(int id, Pageable pageable);

    public List<Exam> findByCourse_Id(int id);

}
