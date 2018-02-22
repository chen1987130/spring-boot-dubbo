package com.feiniu.data.kafka.stream.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(Sink.class)
public class KafkaReceiver {

	private final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

	@StreamListener(Sink.INPUT)
	private void receive(String tag) {
		logger.info("receive message : " + tag);
	}

}