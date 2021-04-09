package com.cattailstore.store.service;

import javax.transaction.Transactional;
import com.cattailstore.store.configuration.BasicTest;
import com.cattailstore.store.model.mysql.Book;
import com.cattailstore.store.repository.mysql.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
@SpringBootTest
public class BookServiceTest extends BasicTest {

    @Autowired
    public BookService service;

    @Autowired
    public BookRepository repository;

    @Test
    @Transactional
    public void whenFindBookByIdReturnBook(){

        // given
        Book book = new Book();
        book.setTitle("New book");
        book = repository.save(book);

        // when
        Book result = service.findBookById(book.getId());

        // then
        assertThat(result,
            hasProperty("title", equalTo("New book"))
        );
    }
}
