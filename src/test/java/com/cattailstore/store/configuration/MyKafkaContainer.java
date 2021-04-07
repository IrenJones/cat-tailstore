package com.cattailstore.store.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@TestConfiguration
public class MyKafkaContainer {

    @Bean
    public KafkaContainer kafkaContainer(){
        KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));
        kafka.start();
        log.info("\uD83D\uDC33 Test kafka is running ..." + kafka.getContainerName());
        return kafka;
    }
}
