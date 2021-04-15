package com.cattailstore.store.service;

import javax.transaction.Transactional;
import com.cattailstore.store.configuration.BasicTest;
import com.cattailstore.store.error.BookDoesntExistException;
import com.cattailstore.store.model.mysql.Book;
import com.cattailstore.store.repository.mysql.BookRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookServiceTest extends BasicTest {

    @Autowired
    public BookService service;

    @Autowired
    public BookRepository repository;

    @SneakyThrows
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
