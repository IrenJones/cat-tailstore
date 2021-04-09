package com.cattailstore.store.service.impl;

import java.util.List;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import com.cattailstore.store.dto.FullBookDto;
import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.model.mysql.Book;
import com.cattailstore.store.repository.mongodb.BookFullRepository;
import com.cattailstore.store.repository.mysql.BookRepository;
import com.cattailstore.store.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	public BookRepository repository;

	@Autowired
	public BookFullRepository bookFullRepository;

	@Autowired
	public ObjectMapper mapper;

	@Override
	public Book findBookById(long id) {
		//todo add handling exception
		if(repository.existsById(id)){
			return repository.findById(id);
		}
		return null;
	}

	@Override
	public BookFull getBookWithDescriptionById(long bookId) {
		//todo add handling exception
		return repository.existsById(bookId)
				? bookFullRepository.findByBookId(bookId)
				: null;
	}

	@Override
	public List<BookFull> getAll() {
		return bookFullRepository.findAll();
	}

	@Override
	public FullBookDto update(long bookId, FullBookDto updatedBook) {
		if(repository.existsById(bookId)){
			BookFull full = bookFullRepository.findByBookId(bookId);
			Book book = repository.findById(bookId);

			if(!updatedBook.getDescription().equals(full.getDescription())){
				full.setDescription(updatedBook.getDescription());
			}

			if(!updatedBook.getTitle().equals(full.getTitle())) {
				full.setTitle(updatedBook.getTitle());
				book.setTitle(updatedBook.getTitle());
			}

			repository.save(book);
			bookFullRepository.save(full);

			return mapper.convertValue(bookFullRepository.findByBookId(bookId), FullBookDto.class);
		}

		return null;
	}

	@Transactional(Transactional.TxType.REQUIRED)
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Override
	public void synchronize() {
		List<BookFull> books = bookFullRepository.findAll();
		for(BookFull book: books) {
			if(book.getBookId() == null) {
				Book newBook = new Book();
				newBook.setTitle(book.getTitle());
				newBook = repository.save(newBook);
				book.setBookId(newBook.getId());
				bookFullRepository.save(book);
			}
		}
	}
}
