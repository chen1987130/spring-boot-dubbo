package com.feiniu.gateway;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Test {

	public static void main(String[] args) {

	}

	private void t1() {
		ClassPathResource res = new ClassPathResource("bean.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(res);
	}

	private void t2() {
		ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:appcontext.xml");
		factory.getBean("");
	}
}
