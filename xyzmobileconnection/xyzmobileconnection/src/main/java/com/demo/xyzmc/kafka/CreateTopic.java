package com.demo.xyzmc.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CreateTopic {
	
	@Bean
	public NewTopic KafkaTopicConfig(){
		return TopicBuilder.name("product").build();
	}
	@Bean
	public NewTopic KafkaTopicConfig1(){
		return TopicBuilder.name("product1").build();
	}
}
