package com.cattailstore.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    public BookService service;

    @Test
    public void test(){
        Assert.notNull(service);
    }
}
