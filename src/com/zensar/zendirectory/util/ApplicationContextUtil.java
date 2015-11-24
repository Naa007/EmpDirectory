package com.zensar.zendirectory.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {

	private ApplicationContextUtil(){}
	
	private static ApplicationContext context;
	
	public static ApplicationContext getApplicationContext(){
		if(context == null)
		synchronized (ApplicationContextUtil.class) {
			if(context == null ){
				return new ClassPathXmlApplicationContext("spring-configuration.xml");
			}
		}
		return context;
	}
}
