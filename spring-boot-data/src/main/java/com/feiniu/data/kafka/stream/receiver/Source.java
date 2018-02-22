package com.feiniu.data.kafka.stream.receiver;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {

	// 发送
	@Output("ouput")
	MessageChannel output();
}