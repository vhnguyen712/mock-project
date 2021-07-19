package com.lms.admin.course;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.lms.admin.chapter.ChapterRepository;
import com.lms.admin.resource.ResourceRepository;
import com.lms.commom.entity.Chapter;
import com.lms.commom.entity.Course;
import com.lms.commom.entity.Resources;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CourseRepoTest {

	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	TestEntityManager manager;
	
	@Autowired
	ResourceRepository resourceRepository;
	
	@Test
	public void testAddChapter() {
		
		Course course = manager.find(Course.class, 1);
		
		Chapter chapter2 = new Chapter(4,"chapter3", "haha3", course);
		
		Chapter savedChapter = chapterRepository.save(chapter2);
		
		assertThat(savedChapter.getId()).isGreaterThan(1);
		
	}
	
	@Test
	public void testAddReSourseForChapter() {
		
		Chapter chapter = manager.find(Chapter.class, 2);
		
		Resources resources = new Resources(1,"Day la resources",chapter);
		
		Resources savedResources = resourceRepository.save(resources);
		
		assertThat(savedResources.getId()).isGreaterThan(0);
	}
}
