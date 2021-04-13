package com.cattailstore.store.service;

import java.util.List;
import com.cattailstore.store.dto.FullBookDto;
import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.model.mysql.Book;

public interface BookService {

	Book findBookById (long bookId);

	BookFull getBookWithDescriptionById(long bookId);

	List<BookFull> getAll();

	FullBookDto update(long id, FullBookDto book);

	void uploadData();

	void synchronize();
}
