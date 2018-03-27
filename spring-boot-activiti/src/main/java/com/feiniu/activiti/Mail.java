package com.feiniu.activiti;

import org.apache.commons.mail.SimpleEmail;

public class Mail {

	public static void main(String[] args) {
		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.qq.com");
			email.setSmtpPort(587);
			email.setStartTLSEnabled(true);
			email.setSSLOnConnect(false);
			// 收件人的邮箱
			email.addTo("251382101@qq.com", "chensheng");
			// 发送人的邮箱
			email.setFrom("251382101@qq.com", "鸡博士");
			// 邮件认证
			email.setAuthentication("251382101@qq.com", "xxxxxx");
			email.setSubject("Test message");
			email.setMsg("This is a simple test of commons-email..");
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
