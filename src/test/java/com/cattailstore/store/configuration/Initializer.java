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
            .of("spring.data.mongodb.uri=" + MongoContainer.getInstance().getContainerIpAddress() + ":" + MongoContainer.getInstance().getExposedPorts().get(0))
            .and("kafka.bootstrapAddress=" + MyKafkaContainer.getInstance().getContainerIpAddress() + ":" + MyKafkaContainer.getInstance().getExposedPorts().get(0))
            .applyTo(configurableApplicationContext.getEnvironment());

        log.info("Init finished");
    }
}
