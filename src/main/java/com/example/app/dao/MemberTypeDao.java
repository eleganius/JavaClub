package com.example.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.MemberType;

@Mapper
public interface MemberTypeDao {

	List<MemberType> selectAll() throws Exception;

}
