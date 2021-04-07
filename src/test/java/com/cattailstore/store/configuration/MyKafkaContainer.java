package com.cattailstore.store.configuration;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@TestConfiguration
public class MyKafkaContainer {

    private static KafkaContainer containerInst;

    public static KafkaContainer getInstance() {
        if (containerInst == null) {
            containerInst = kafkaContainer();
        }
        return containerInst;
    }

    private static KafkaContainer kafkaContainer(){
        KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));
        kafka.start();
        log.warn("------> Test kafka is running ..." + kafka.getContainerName());
        return kafka;
    }
}
