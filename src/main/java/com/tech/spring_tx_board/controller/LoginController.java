package com.tech.spring_tx_board.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.spring_tx_board.util.Constant;

@Controller
public class LoginController {
	
	public JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template=this.template;
	}
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/login")
	public String login() {
		System.out.println("pass by login()");

		return "login/loginForm";
		
	}
	@RequestMapping("/loginProc")
	public String loginProc(HttpServletRequest request,Model model) {
		System.out.println("pass by loginProc()");
		String uid=request.getParameter("id");

		//일치처리
		request.getSession().setAttribute("uid", uid);
	
		return "redirect:list";
	}
	
	
	@RequestMapping("/logoutProc")
	public String logoutProc(HttpServletRequest request,Model model) {
		System.out.println("pass by logoutProc()");
		
		request.getSession().invalidate();
		
		return "redirect:list";
	}
	
	
}
