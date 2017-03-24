/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.bbs.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ibaixiong.bbs.service.FormService;
import com.ibaixiong.entity.BbsForm;

/**
 * 头部版块拦截，同时放在request中，
 * 排除ajax请求
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年8月12日
 * @since  V1.5.0
 */
public class FormInterceptor implements HandlerInterceptor {
	Logger logger=LoggerFactory.getLogger(getClass());
	@Resource
	private FormService formService;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//ajax请求排除
		String requestType=request.getHeader("X-Requested-With");
		if(requestType==null||!requestType.equals("XMLHttpRequest")){
			List<BbsForm> formList = formService.getFormByParentId(0l);
			request.setAttribute("commonFormList", formList);
			logger.debug("进入版块拦截了");
		}
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
