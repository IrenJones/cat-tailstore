package com.cattailstore.store.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@TestConfiguration
public class MysqlContainer {

    @Bean
    public MySQLContainer<?> mySQlContainer(){
        MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:5.6"));
        mysql.start();
        log.info("\uD83D\uDC33 MySQL Db is running ..." + mysql.getContainerName());
        return mysql;
    }
}
