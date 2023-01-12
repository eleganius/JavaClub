package com.example.app.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.NewsForm;
import com.example.app.service.MemberService;
import com.example.app.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	NewsService newsService;

	@Autowired
	MemberService memberService;

	@InitBinder
	public void initBinderForm(WebDataBinder binder) {
		var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(dateFormat, true));
	}

	@GetMapping
	public String list(Model model) throws Exception {
		model.addAttribute("newsList", newsService.getNewsList());
		return "news/list";
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("news", newsService.getNewsById(id));
		return "news/detail";
	}

	@GetMapping("/add")
	public String addGet(Model model) throws Exception {
		model.addAttribute("memberTypeList", memberService.getTypeList());
		model.addAttribute("newsForm", new NewsForm());
		return "news/add";
	}

	@PostMapping("/add")
	public String addPost(
			HttpSession session,
			@Valid NewsForm newsForm,
			Errors errors,
			Model model) throws Exception {
		//バリデーション
		if (errors.hasErrors()) {

			// エラー内容の確認
			List<ObjectError> objList = errors.getAllErrors();
			for (ObjectError obj : objList) {
				System.out.println(obj.toString());
			}

			model.addAttribute("memberTypeList", memberService.getTypeList());
			return "news/add";
		}

		//投稿者名は管理者のログインIDとする
		newsForm.setAuthor((String) session.getAttribute("loginId"));

		//データベースへ追加
		newsService.addNews(newsForm);
		return "redirect:/news?status=add";
	}

}
