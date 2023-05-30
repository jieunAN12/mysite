package com.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysite.service.UserService;
import com.mysite.vo.UserVo;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/* 회원가입폼 */
	@RequestMapping(value="/user/joinForm", method= {RequestMethod.GET, RequestMethod.POST} )
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "/WEB-INF/views/user/joinForm.jsp";
	}
	
	
	/* 회원가입 */
	@RequestMapping(value="/user/join", method= {RequestMethod.GET, RequestMethod.POST} )
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		
		int count = userService.join(userVo);
		//count = 0;
		
		if(count>0) {
			return "/WEB-INF/views/user/joinOk.jsp";
		} else {
			return "redirect:/user/joinForm";
		}
		
	}
	
	/* 로그인 폼 */
	@RequestMapping(value="/user/loginForm", method= {RequestMethod.GET, RequestMethod.POST} )
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		return "/WEB-INF/views/user/loginForm.jsp";
	}
	
	/* 로그인 */
	@RequestMapping(value="/user/login", method= {RequestMethod.GET, RequestMethod.POST} )
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.login()");
		
		UserVo authUser = userService.login(userVo);
		System.out.println(authUser);
		
		if(authUser != null) {//로그인성공했을때
			System.out.println("로그인성공");
			//세션에 저장
			session.setAttribute("authUser", authUser);
			//메인으로 리다이렉트
			return "redirect:/main";
			
		}else { //로그인실패했을때
			System.out.println("로그인실패");
			//로그인폼
			
			return "redirect:/user/loginForm?result=fail";
		}
		
		
	}
	
	/* 로그아웃 */
	@RequestMapping(value="/user/logout", method= {RequestMethod.GET, RequestMethod.POST} )
	public String logout(HttpSession session) {
		System.out.println("UserController.login()");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	/* 회원정보 수정폼 */
	@RequestMapping(value="/user/modifyForm", method= {RequestMethod.GET, RequestMethod.POST} )
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("UserController.modifyForm()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		UserVo userVo = userService.modifyForm(no);
		
		model.addAttribute("userVo", userVo);
		
		return "/WEB-INF/views/user/modifyForm.jsp";
	}
	
	/* 회원정보 수정 */
	@RequestMapping(value="/user/modify", method= {RequestMethod.GET, RequestMethod.POST} )
	public String modify(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.modify()");
		
		int count = userService.modify(userVo);
		//count = 0;
		
		if(count>0) {
			return "/WEB-INF/views/main/index.jsp";
		} else {
			return "redirect:/user/modifyForm";
		}
		
	}

}
