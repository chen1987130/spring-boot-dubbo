package com.feiniu.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feiniu.data.kafka.stream.receiver.KafkaSender;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

	@Value("${kafka.topic}")
	private String topic;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private KafkaSender sender;

	@RequestMapping("/send")
	public String send(@RequestParam String msg) {
		sender.sendMessage(msg);
		return "success";
	}

	@RequestMapping("/sendMsg")
	public String sendMsg(@RequestParam String msg) {
		kafkaTemplate.send(topic, msg);
		return "success";
	}
}
