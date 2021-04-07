package com.cattailstore.store.service;

import javax.transaction.Transactional;
import com.cattailstore.store.configuration.MongoContainer;
import com.cattailstore.store.configuration.MyKafkaContainer;
import com.cattailstore.store.configuration.MysqlContainer;
import com.cattailstore.store.model.mysql.Book;
import com.cattailstore.store.repository.mysql.BookRepository;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
public class BookServiceTest {

    @ClassRule
    public static MySQLContainer<?> msqContainer = MysqlContainer.getInstance();

    @ClassRule
    public static MongoDBContainer mongoContainer = MongoContainer.getInstance();

    @ClassRule
    public static KafkaContainer kafkaContainer = MyKafkaContainer.getInstance();

    @Autowired
    public BookService service;

    @Autowired
    public BookRepository repository;

    @Test
    @Transactional
    public void test(){

        // given
        Book book = new Book();
        book.setTitle("New book");
        book = repository.save(book);

        // when
        Book result = service.findBookById(book.getId());

        Assert.assertEquals(result.getTitle(), book.getTitle());
    }
}
