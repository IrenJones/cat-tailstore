package com.cattailstore.store.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@TestConfiguration
public class MongoContainer {

    @Bean
    public MongoDBContainer mongoContainer() {
        final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
        mongoDBContainer.start();
        log.info("\uD83D\uDC33 MongoDb is running ..." + mongoDBContainer.getContainerName());
        return mongoDBContainer;
    }
}
