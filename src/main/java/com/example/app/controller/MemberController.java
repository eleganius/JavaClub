package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
