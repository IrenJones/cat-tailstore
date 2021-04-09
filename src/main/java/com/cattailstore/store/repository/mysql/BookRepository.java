package com.cattailstore.store.repository.mysql;

import java.util.List;
import com.cattailstore.store.model.mysql.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book findById(long id);

    List<Book> findAll();
}
