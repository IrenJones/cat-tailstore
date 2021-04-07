package com.cattailstore.store.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@TestConfiguration
public class MongoContainer {

    private static MongoDBContainer containerInst;

    public static MongoDBContainer getInstance() {
        if (containerInst == null) {
            containerInst = mongoContainer();
        }
        return containerInst;
    }

    private static MongoDBContainer mongoContainer() {
        MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
        container.start();
        log.warn("------> MongoDb is running ..." + container.getContainerName());
        return container;
    }

//    @DynamicPropertySource
//    static void mongoDbProperties(DynamicPropertyRegistry registry, MongoDBContainer mongoDBContainer) {
//        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }
}
