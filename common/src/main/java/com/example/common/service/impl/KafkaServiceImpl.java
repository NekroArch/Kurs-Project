package com.example.common.service.impl;

import com.example.common.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent %s", message));
        kafkaTemplate.send(topicName, message);
    }
}