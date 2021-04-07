package com.cattailstore.store.repository;

import com.cattailstore.store.configuration.MysqlContainer;
import com.cattailstore.store.repository.mysql.BookRepository;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
public class BookRepositoryTest {

    @ClassRule
    public static MySQLContainer<?> mySQLContainer = MysqlContainer.getInstance();

    @Autowired
    public BookRepository repository;

    @Test
    public void testConnection() {
        Assert.assertEquals(0, repository.count());
    }
}
