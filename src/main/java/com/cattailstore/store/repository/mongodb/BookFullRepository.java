package com.cattailstore.store.repository.mongodb;

import com.cattailstore.store.model.mongodb.BookFull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookFullRepository extends MongoRepository<BookFull, String> {

	BookFull findByTitle(String title);
	BookFull findByBookId(Long bookId);
}
