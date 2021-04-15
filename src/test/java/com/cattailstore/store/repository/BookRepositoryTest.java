package com.cattailstore.store.repository;

import java.util.List;
import com.cattailstore.store.configuration.BasicTest;
import com.cattailstore.store.model.mysql.Book;
import com.cattailstore.store.repository.mysql.BookRepository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class BookRepositoryTest extends BasicTest {

    @Autowired
    public BookRepository repository;

    @Test
    public void testConnection() {
        List<Book> result = repository.findAll();
        assertThat(result, hasSize(0));
    }
}
