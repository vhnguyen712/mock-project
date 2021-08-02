/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.admin.member;

import com.lms.commom.entity.CourseMember;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Admin
 */
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/ban_member")
    public String listFirstPage(Model model) {
        return listByPage(1, model, null);
    }

    @GetMapping("/ban_member/page/{pageNum}")
    public String listByPage(@PathVariable("pageNum") int pageNum, Model model, @Param("keyword") String keyword) {
        Page<CourseMember> page = memberService.listByPagee(pageNum, keyword);
        List<CourseMember> listMembers = page.getContent();
        long totalItem = page.getTotalElements();
        int totalPage = page.getTotalPages();
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("listMembers", listMembers);
        model.addAttribute("keyword", keyword);
        return "member/ban_member";
    }

    @GetMapping("/ban_member/{id}/enable/{status}")
    public String updateEnable(@PathVariable("id") Integer id, @PathVariable("status") boolean enable, RedirectAttributes redirectAttributes) {

        memberService.update(id, enable);
        String msg = enable ? "Enabled" : "Disable";

        redirectAttributes.addFlashAttribute("message", msg);

        return "redirect:/ban_member";
    }

}
