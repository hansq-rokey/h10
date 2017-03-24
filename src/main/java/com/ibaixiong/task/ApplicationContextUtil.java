package com.ibaixiong.task;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware{
	private static ApplicationContext context; 
	@Override
	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		this.context=contex; 
	}
	public static ApplicationContext getContext(){ 
	  return context; 
	} 
}
