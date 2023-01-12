package com.example.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NewsTargetDao {

	void insert(@Param("newsId") Integer newsId, @Param("typeId") Integer typeId) throws Exception;
}
