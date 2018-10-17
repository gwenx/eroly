package com.eroly.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class SpringTools implements ApplicationContextAware{
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		SpringTools.applicationContext = arg0;
	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static Object getBean(String beanName) {
		return null!=getApplicationContext()?getApplicationContext().getBean(beanName):null;
	}
	public static <T> T getBean(String beanName,Class<T> clazz) {
		return null!=getApplicationContext()?getApplicationContext().getBean(beanName,clazz):null;
	}

}
