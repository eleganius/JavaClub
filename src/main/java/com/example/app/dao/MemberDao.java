package com.example.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Member;

@Mapper
public interface MemberDao {

	List<Member> selectAll() throws Exception;

	Member selectById(Integer id) throws Exception;

	void insert(Member member) throws Exception;

	void update(Member member) throws Exception;

	void delete(Integer id) throws Exception;

}
