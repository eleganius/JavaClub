package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.NewsDao;
import com.example.app.domain.News;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsDao newsDao;

	@Override
	public List<News> getNewsList() throws Exception {
		return newsDao.selectAll();
	}

}
