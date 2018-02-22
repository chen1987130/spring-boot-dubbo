package com.feiniu.data.kafka.stream.send;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink {
	// 接收队列
	String INPUT = "input";

	@Input(Sink.INPUT)
	SubscribableChannel input();
}
