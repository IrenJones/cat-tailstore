package com.cattailstore.store.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues
            .of("spring.data.mongo.uri=" + MongoContainer.getInstance().getReplicaSetUrl())
            .applyTo(configurableApplicationContext.getEnvironment());

        log.info("Init finished");
    }
}
