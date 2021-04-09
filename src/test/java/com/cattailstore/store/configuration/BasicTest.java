package com.cattailstore.store.configuration;

import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MySQLContainer;

@ContextConfiguration(initializers = Initializer.class)
@SpringBootTest
public class BasicTest {

    @ClassRule
    public static MySQLContainer<?> mySQLContainer = MysqlContainer.getInstance();

    @ClassRule
    public static MongoDBContainer mongoDBContainer = MongoContainer.getInstance();

    @ClassRule
    public static KafkaContainer kafkaContainer = MyKafkaContainer.getInstance();
}
