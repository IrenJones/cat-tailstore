package com.cattailstore.store.kafka;

import com.cattailstore.store.shared.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info(String.format("Message sent -> %s", message));
        this.kafkaTemplate.send(Topics.MAIN_TOPIC, message);
    }
}
