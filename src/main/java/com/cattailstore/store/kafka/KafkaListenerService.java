package com.cattailstore.store.kafka;

import java.util.ArrayList;
import java.util.List;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    List<String> testList = new ArrayList<>();

    @KafkaListener(topics = "bookstore-events", groupId = "events")
    public void getMessageFromMainTopic(String message) {
        System.out.println("Received Message in group events: " + message);
        testList.add(message);
    }
}
