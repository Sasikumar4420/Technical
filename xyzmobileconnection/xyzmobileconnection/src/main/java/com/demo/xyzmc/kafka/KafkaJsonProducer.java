package com.demo.xyzmc.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.demo.xyzmc.entity.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaJsonProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(Object customer) {
		
		Message<Object> message = MessageBuilder.withPayload(customer).setHeader(KafkaHeaders.TOPIC, "product")
				.build();
		kafkaTemplate.send(message);
		log.info(String.format("messeage sent %s", message));

//		Properties props = new Properties();
//		props.put("bootstrap.servers", "localhost:9092");
//		@SuppressWarnings("unchecked")
//		Producer<String, Customer> kafkaProducer = new KafkaProducer<>(props, new StringSerializer(),
//				new KafkaJsonSerializer());
//		log.info("message --->"+kafkaProducer.toString());
//		kafkaProducer.send(new ProducerRecord<String, Customer>("product", customer));
	
	}
}
