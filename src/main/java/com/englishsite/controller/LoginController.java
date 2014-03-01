package com.englishsite.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.englishsite.po.Account;
import com.englishsite.po.Auth;
import com.englishsite.service.AccountService;


/**
 * 用户登录功能
 * @author wangkui
 *
 */
@Controller(value = "loginController")
public class LoginController extends UserBaseController {
	
	@Resource(name = "accountService")
	private AccountService accountService;
	
	/**
	 * 打开登录页面
	 */
	@RequestMapping("/login.do")
	public ModelAndView study(HttpServletRequest request, HttpServletResponse response)
	{
		//打开注册页面时，需要准备的一些数据
		return new ModelAndView("/login");
	}
	
	/**
	 * 注册
	 */
	@RequestMapping("/account.do")
	public ModelAndView tryEnd(HttpServletRequest request, HttpServletResponse response)
	{
		String passport = getNotNull("passport", request);
		String password = getNotNull("password", request);
		
		//TODO 对字段做检查
		
		boolean flag = accountService.login(passport, password);
		if(flag)
		{//登录成功，加载用户信息
			
			return new ModelAndView("/account");
		}
		return new ModelAndView("/login");
	}

}
