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
 * 用户注册功能
 * @author wangkui
 *
 */
@Controller(value = "registerController")
public class RegisterController extends UserBaseController {
	
	@Resource(name = "accountService")
	private AccountService accountService;
	
	/**
	 * 打开准备注册页面
	 */
	@RequestMapping("/preReg.do")
	public ModelAndView study(HttpServletRequest request, HttpServletResponse response)
	{
		//打开注册页面时，需要准备的一些数据
		return new ModelAndView("/preReg");
	}
	
	/**
	 * 注册
	 */
	@RequestMapping("/register.do")
	public ModelAndView tryEnd(HttpServletRequest request, HttpServletResponse response)
	{
		String passport = getNotNull("passport", request);
		String password = getNotNull("password", request);
		String email = getNotNull("email", request);
		//……其它字段
		
		//TODO 对字段做检查
		
		Account account = new Account();
		account.setPassport(passport);
		account.setEmail(email);
		
		Auth auth = new Auth();
		auth.setPassport(passport);
		auth.setPassword(password);
		boolean flag = accountService.registerAccountInfo(account, auth);
		//TODO 这里改成Ajax，注册的错误提示应该在页面直接提示
		if(flag){
			return new ModelAndView("/regSuccess");
		}
		return new ModelAndView("/regFail");
	}

}
