package com.lms.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lms.commom.entity.User;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/users")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, null);
	}
	
	@GetMapping("/users/page/{pageNum}")
	public String listByPage(@PathVariable("pageNum")int pageNum, Model model,@Param("keyword") String keyword) {
		Page<User> page = userService.listByPage(pageNum, keyword );
		List<User> listUsers = page.getContent();

		long totalItem = page.getTotalElements();
		int totalPage = page.getTotalPages();
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("totalItem", totalItem);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("keyword", keyword);
		
		return "user/users";
	}
	
	
	@GetMapping("/users/{id}/enable/{status}")
	public String updateEnable(@PathVariable("id")Integer id,@PathVariable("status") boolean enable,RedirectAttributes redirectAttributes) {
		
		userService.updateEnable(id, enable);
		String msg = enable ? "Enabled" : "Disable";
		
		redirectAttributes.addFlashAttribute("message", msg);
		
		return "redirect:/user";
	}
	
	@GetMapping("/users/export/csv")
	public void exportCSV(HttpServletResponse response) throws IOException {
		
		List<User> listUsers = userService.getListUsers();
		
		UserCsvExporter  exporter = new UserCsvExporter();
		
		exporter.export(listUsers, response);
	}
}
