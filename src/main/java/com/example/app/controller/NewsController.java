package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	NewsService newsService;

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

}
