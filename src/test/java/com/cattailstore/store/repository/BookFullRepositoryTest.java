package com.cattailstore.store.repository;

import com.cattailstore.store.configuration.BasicTest;
import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.repository.mongodb.BookFullRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class BookFullRepositoryTest extends BasicTest {

    @Autowired
    public BookFullRepository repository;

    @Test
    public void testConnectionOne(){
        BookFull book = new BookFull("1", 1L, "Sherlock", "Holmes is here", "Dadad", 2, "", "");
        repository.save(book);
        Assert.assertEquals("Sherlock", repository.findByBookId(1L).getTitle());
    }

    @Test
    public void testConnectionTwo(){
        BookFull book = new BookFull("1", 1L, "Sherlock", "Holmes is here", "Da", 2, "", "");
        repository.save(book);
        book = new BookFull("2", 2L, "Sherlock", "Holmes is here", "Da", 2, "", "");
        repository.save(book);
        Assert.assertEquals(2, repository.count());
    }

    @AfterEach
    public void clean(){
        repository.deleteAll();
    }
}
