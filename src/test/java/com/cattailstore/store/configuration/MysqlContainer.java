package com.cattailstore.store.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@TestConfiguration
public class MysqlContainer {

    private static MySQLContainer<?> containerInst;

    public static MySQLContainer<?> getInstance() {
        if (containerInst == null) {
            containerInst = mySQlContainer();
        }
        return containerInst;
    }

    private static MySQLContainer<?> mySQlContainer(){
        MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:5.6"));
        mysql.start();
        log.warn("------> MySQL Db is running ..." + mysql.getContainerName());
        return mysql;
    }
}
