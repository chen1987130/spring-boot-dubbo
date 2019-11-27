package com.feiniu.activiti;

import org.apache.commons.mail.SimpleEmail;

public class Mail {

	public static void main(String[] args) {
		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.dtinsure.com");
			email.setSmtpPort(25);
			email.setStartTLSEnabled(false);
			email.setSSLOnConnect(false);
			// 收件人的邮箱
			email.addTo("xxx@qq.com", "chensheng");
			// 发送人的邮箱
			email.setFrom("xxx@qq.com", "陈晟");
			// 邮件认证
			email.setAuthentication("xxx@qq.com", "");
			email.setSubject("Test message");
			email.setMsg("This is a simple test of commons-email..");
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
