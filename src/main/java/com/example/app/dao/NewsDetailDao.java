package com.example.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.NewsDetail;

@Mapper
public interface NewsDetailDao {

	void insert(NewsDetail detail);

}
