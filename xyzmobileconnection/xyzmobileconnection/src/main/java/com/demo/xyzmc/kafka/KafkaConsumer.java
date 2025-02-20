package com.demo.xyzmc.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
	
//	@KafkaListener(topics = "product1",groupId = "mygroup")
//	public void consume(String message) {
//		log.info("message retrieved->"+message);
//	}
}
