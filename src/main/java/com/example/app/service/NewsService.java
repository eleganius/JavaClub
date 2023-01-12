package com.example.app.service;

import java.util.List;

import com.example.app.domain.News;

public interface NewsService {

	List<News> getNewsList() throws Exception;

}
