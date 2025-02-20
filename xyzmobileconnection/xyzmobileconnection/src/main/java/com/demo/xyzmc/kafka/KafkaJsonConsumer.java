package com.demo.xyzmc.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.demo.xyzmc.entity.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaJsonConsumer {
	
//	@KafkaListener(topics = "product",groupId = "mygroup")
//	public void consume(Customer customer) {
//		log.info("customer data retrieved ->"+ customer);
//	}
}
