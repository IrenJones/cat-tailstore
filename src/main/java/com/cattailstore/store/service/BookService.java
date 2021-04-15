package com.cattailstore.store.service;

import java.util.List;
import com.cattailstore.store.dto.FullBookDto;
import com.cattailstore.store.error.BookDoesntExistException;
import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.model.mysql.Book;

public interface BookService {

	Book findBookById (long bookId) throws BookDoesntExistException;

	BookFull getBookWithDescriptionById(long bookId) throws BookDoesntExistException;

	List<BookFull> getAll();

	FullBookDto update(long id, FullBookDto book) throws BookDoesntExistException;

	void uploadData();

	void synchronize();
}
