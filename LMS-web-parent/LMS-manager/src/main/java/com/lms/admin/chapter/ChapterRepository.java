package com.lms.admin.chapter;


import com.lms.commom.entity.Chapter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    public List<Chapter> findByCourse_Id(int id);

}
