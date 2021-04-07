package com.cattailstore.store.repository;

import javax.transaction.Transactional;
import com.cattailstore.store.configuration.MongoContainer;
import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.repository.mongodb.BookFullRepository;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest
public class BookFullRepositoryTest {

    @ClassRule
    public static MongoDBContainer mongoDBContainer = MongoContainer.getInstance();

    @Autowired
    public BookFullRepository repository;

    @Test
    @Transactional
    public void testConnectionOne(){
        BookFull book = new BookFull("1", 1L, "Sherlock", "Holmes is here");
        repository.save(book);
        Assert.assertEquals("Sherlock", repository.findByBookId(1L).getTitle());
    }

    @Test
    @Transactional
    public void testConnectionTwo(){
        BookFull book = new BookFull("1", 1L, "Sherlock", "Holmes is here");
        repository.save(book);
        book = new BookFull("2", 2L, "Sherlock", "Holmes is here");
        repository.save(book);
        Assert.assertEquals(2, repository.count());
    }
}
