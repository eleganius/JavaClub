package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dao.NewsDao;
import com.example.app.dao.NewsDetailDao;
import com.example.app.dao.NewsTargetDao;
import com.example.app.domain.News;
import com.example.app.domain.NewsDetail;
import com.example.app.domain.NewsForm;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsDao newsDao;

	@Autowired
	NewsDetailDao newsDetailDao;

	@Autowired
	NewsTargetDao newsTargetDao;

	@Override
	public List<News> getNewsList() throws Exception {
		return newsDao.selectAll();
	}

	@Override
	public News getNewsById(Integer id) throws Exception {
		return newsDao.selectById(id);
	}

	@Override
	@Transactional
	public void addNews(NewsForm formData) throws Exception {
		//newsテーブルへの追加
		News news = new News();
		news.setTitle(formData.getTitle());
		news.setAuthor(formData.getAuthor());
		news.setPostDate(formData.getPostDate());
		newsDao.insert(news); //newsにidがセットされる

		//news_detailテーブルへの追加
		NewsDetail detail = new NewsDetail();
		detail.setNewsId(news.getId());
		detail.setArticle(formData.getArticle());
		newsDetailDao.insert(detail);

		//news_targetsテーブルへの追加
		for (Integer targetId : formData.getTargetIdList()) {
			newsTargetDao.insert(news.getId(), targetId);
		}
	}

}
