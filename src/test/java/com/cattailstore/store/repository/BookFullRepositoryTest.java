package com.cattailstore.store.repository;

import com.cattailstore.store.configuration.MongoContainer;
import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.repository.mongodb.BookFullRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookFullRepositoryTest {

    @Autowired
    public MongoContainer container;

    @Autowired
    public BookFullRepository repository;

    @Test
    public void testConnectionOne(){
        BookFull book = new BookFull("1", 1L, "Sherlock", "Holmes is here");
        repository.save(book);
        Assert.assertEquals("Sherlock", repository.findByBookId(1L).getTitle());
    }

    @Test
    public void testConnectionTwo(){
        BookFull book = new BookFull("1", 1L, "Sherlock", "Holmes is here");
        repository.save(book);
        book = new BookFull("2", 2L, "Sherlock", "Holmes is here");
        repository.save(book);
        Assert.assertEquals(2, repository.count());
    }
}
