package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dao.MemberDao;
import com.example.app.dao.MemberTypeDao;
import com.example.app.domain.Member;
import com.example.app.domain.MemberType;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	@Autowired
	MemberTypeDao memberTypeDao;

	@Override
	public List<Member> getMemberList() throws Exception {
		return memberDao.selectAll();
	}

	@Override
	public Member getMemberById(Integer id) throws Exception {
		return memberDao.selectById(id);
	}

	@Override
	public void addMember(Member menber) throws Exception {
		memberDao.insert(menber);
	}

	@Override
	public void editMember(Member member) throws Exception {
		memberDao.update(member);
	}

	@Override
	public void deleteMember(Integer id) throws Exception {
		memberDao.delete(id);
	}

	@Override
	public List<MemberType> getTypeList() throws Exception {
		return memberTypeDao.selectAll();
	}

	@Override
	public int getTotalPages(int numPerPage) throws Exception {
		double totalNum = (double) memberDao.count();
		return (int) Math.ceil(totalNum / numPerPage);
	}

	@Override
	public List<Member> getMemeberListByPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return memberDao.selectLimited(offset, numPerPage);
	}

}
