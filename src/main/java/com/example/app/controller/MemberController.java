package com.example.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Member;
import com.example.app.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	MemberService service;

	@GetMapping
	public String list(
			@RequestParam(name = "status", required = false) String status,
			Model model) throws Exception {
		model.addAttribute("statusMessage", getStatusMessage(status));
		model.addAttribute("members", service.getMemberList());
		return "members/list";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) throws Exception {
		service.deleteMember(id);
		return "redirect:/members?status=delete";
	}

	@GetMapping("/add")
	public String addGet(Model model) throws Exception {
		model.addAttribute("member", new Member());
		model.addAttribute("types", service.getTypeList());
		return "members/add";
	}

	@PostMapping("/add")
	public String addPost(
			@Valid Member member,
			Errors errors,
			Model model) throws Exception {
		if (errors.hasErrors()) {
			// エラー内容の確認
			List<ObjectError> objList = errors.getAllErrors();
			for (ObjectError obj : objList) {
				System.out.println(obj.toString());
				model.addAttribute("types", service.getTypeList());
				return "members/add";
			}
		}

		service.addMember(member);
		return "redirect:/members?status=add";
	}

	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("member", service.getMemberById(id));
		model.addAttribute("types", service.getTypeList());
		return "members/edit";
	}

	@PostMapping("/edit/{id}")
	public String editpost(
			@PathVariable Integer id,
			@Valid Member member,
			Errors errors,
			Model model) throws Exception {
		if (errors.hasErrors()) {
			// エラー内容の確認
			List<ObjectError> objList = errors.getAllErrors();
			for (ObjectError obj : objList) {
				System.out.println(obj.toString());
			}
			model.addAttribute("types", service.getTypeList());
			return "members/edit";
		}

		member.setId(id);
		service.editMember(member);
		return "redirect:/members?status=edit";
	}

	//statusの値に応じたメッセージを作成する
	private String getStatusMessage(String status) {
		String message = null;

		if (status == null) {
			return message;
		}

		switch (status) {
		case "add":
			message = "会員を追加しました。";
			break;
		case "edit":
			message = "会員情報を更新しました。";
			break;
		case "delete":
			message = "会員情報を削除しました。";
		}
		return message;
	}

}
