package com.cattailstore.store.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.cattailstore.store.configuration.BasicTest;
import com.cattailstore.store.shared.Topics;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class KafkaTest extends BasicTest{

    @Autowired
    public KafkaProperties kafkaProperties;

    @Autowired
    public KafkaProducerService producerService;

    @Autowired
    public KafkaListenerService listenerService;

    @Test
    public void test(){
        producerService.sendMessage("Hell");

        //Assert.assertEquals("Hell", listenerService.testList.get(0));


        // todo
        Consumer<String, String> consumer = new KafkaConsumer<>(kafkaProperties.buildConsumerProperties());
        consumer.assign(Arrays.asList(new TopicPartition(Topics.MAIN_TOPIC, 0)));
        List<ConsumerRecord<String, String>> records = new ArrayList<>();
        consumer.poll(3).records(Topics.MAIN_TOPIC).forEach(records::add);
        Assert.assertEquals(0, records.size());
    }
}
