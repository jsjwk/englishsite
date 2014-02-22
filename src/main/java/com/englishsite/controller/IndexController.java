package com.englishsite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 首页
 * @author wangkui
 *
 */
@Controller(value = "indexController")
public class IndexController extends UserBaseController {
	
	/**
	 * 首页
	 */
	@RequestMapping("/index.do")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		//TODO
		return new ModelAndView("/index");
	}

}
