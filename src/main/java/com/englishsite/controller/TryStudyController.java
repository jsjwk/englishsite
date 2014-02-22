package com.englishsite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 功能页面（试用学习页面）
 * @author wangkui
 *
 */
@Controller(value = "tryStudyController")
public class TryStudyController extends UserBaseController {
	
	/**
	 * 试用学习页面
	 */
	@RequestMapping("/tryStudy.do")
	public ModelAndView study(HttpServletRequest request, HttpServletResponse response)
	{
		//TODO
		return new ModelAndView("/tryStudy");
	}
	
	/**
	 * 试用学习结束页面
	 */
	@RequestMapping("/tryEnd.do")
	public ModelAndView tryEnd(HttpServletRequest request, HttpServletResponse response)
	{
		//TODO
		return new ModelAndView("/tryEnd");
	}

}
