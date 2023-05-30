package com.mysite.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/*회원가입*/
	public int insertUser(UserVo userVo) {
		System.out.println("UserDao.insertUser()");
		
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
	}
	
	
	/* 로그인 --> 세션저장용*/
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao.selectUser()");
		
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		return authUser;
	}
	
	/* 회원정보 수정폼  회원정보1명 가져오기*/
	public UserVo selectUser(int no) {
		System.out.println("UserDao.selectUser()");
		
		UserVo userVo = sqlSession.selectOne("user.selectUserByNo", no);
		return userVo;
		
	}

}
