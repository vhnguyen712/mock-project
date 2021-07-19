package com.lms.admin.chapter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChapterController {

	@GetMapping("/add_chapter")
	public String showAddChapterForm() {
		return "course/resource/add_chapter_form";
	}
}
