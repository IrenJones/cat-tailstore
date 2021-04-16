package com.cattailstore.store.configuration;

import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MySQLContainer;

@ContextConfiguration(initializers = Initializer.class)
public abstract class BasicTest {

    public static final MySQLContainer<?> mySQLContainer = MysqlContainer.getInstance();

    public static final MongoDBContainer mongoDBContainer = MongoContainer.getInstance();

    public static final KafkaContainer kafkaContainer = MyKafkaContainer.getInstance();
}
