package com.lms.admin.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lms.commom.entity.Manager;

@Controller
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	@GetMapping("/managers")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, null);
	}
	
	@GetMapping("/managers/page/{pageNum}")
	public String listByPage(@PathVariable("pageNum")int pageNum, Model model,@Param("keyword") String keyword) {
		Page<Manager> page = managerService.listByPage(pageNum, keyword );
		List<Manager> listManagers = page.getContent();

		long totalItem = page.getTotalElements();
		int totalPage = page.getTotalPages();
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("totalItem", totalItem);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("listManagers", listManagers);
		model.addAttribute("keyword", keyword);
		
		return "manager/managers";
	}
	
	@GetMapping("/managers/{id}/enable/{status}")
	public String updateEnable(@PathVariable("id")Integer id,@PathVariable("status") boolean enable,RedirectAttributes redirectAttributes) {
		
		managerService.updateEnable(id, enable);
		String msg = enable ? "Enabled" : "Disable";
		
		redirectAttributes.addFlashAttribute("message", msg);
		
		return "redirect:/managers";
	}
	
//	@GetMapping("/managers/delete/{id}")
//	public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Model model) {
//
//		try {
//			managerService.delete(id);
//			redirectAttributes.addFlashAttribute("message", "The manager have been deleted");
//
//		} catch (UsernameNotFoundException e) {
//			redirectAttributes.addFlashAttribute("message", e.getMessage());
//		}
//		return "redirect:/managers";
//	}
}
