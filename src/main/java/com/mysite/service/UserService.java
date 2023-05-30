package com.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mysite.dao.UserDao;
import com.mysite.vo.UserVo;

@Controller
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/* 회원등록 */
	public int join(UserVo userVo) {
		System.out.println("UserService.join()");
		
		int count = userDao.insertUser(userVo);
		return count;
	}
	
	/*로그인*/
	public UserVo login(UserVo userVo) {
		System.out.println("UserService.login()");
		
		UserVo authUser = userDao.selectUser(userVo);
		return authUser;
	}
	
	/* 회원정보 수정폼 */
	public UserVo modifyForm(int no) {
		System.out.println("UserService.modifyForm()");
		
		UserVo userVo = userDao.selectUser(no);
		return userVo;
	}
	

}
