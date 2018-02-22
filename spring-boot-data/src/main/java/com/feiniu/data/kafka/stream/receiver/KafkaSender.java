package com.feiniu.data.kafka.stream.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(Source.class)
public class KafkaSender {

	@Autowired
	private Source source;

	public void sendMessage(String message) {
		try {
			source.output().send(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}