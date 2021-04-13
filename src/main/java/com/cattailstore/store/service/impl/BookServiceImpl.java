package com.cattailstore.store.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import com.cattailstore.store.dto.FullBookDto;
import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.model.mysql.Book;
import com.cattailstore.store.repository.mongodb.BookFullRepository;
import com.cattailstore.store.repository.mysql.BookRepository;
import com.cattailstore.store.service.BookService;
import com.cattailstore.store.service.ItBookstoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public BookRepository repository;

    @Autowired
    public BookFullRepository bookFullRepository;

    @Autowired
    public ItBookstoreService itBookstoreService;

    @Autowired
    public ObjectMapper mapper;

    @Override
    public Book findBookById(long id) {
        //todo add handling exception
        if (repository.existsById(id)) {
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
        if (repository.existsById(bookId)) {
            BookFull full = bookFullRepository.findByBookId(bookId);
            Book book = repository.findById(bookId);

            if (!updatedBook.getDescription().equals(full.getDescription())) {
                full.setDescription(updatedBook.getDescription());
            }

            if (!updatedBook.getTitle().equals(full.getTitle())) {
                full.setTitle(updatedBook.getTitle());
                book.setTitle(updatedBook.getTitle());
            }

            repository.save(book);
            bookFullRepository.save(full);

            return mapper.convertValue(bookFullRepository.findByBookId(bookId), FullBookDto.class);
        }

        return null;
    }

    @Override
    public void uploadData(){
        String json = itBookstoreService.getJsonResultByTopic("mongo");
        JsonParser parser = JsonParserFactory.getJsonParser();
        List<Map<String, String>> books = (List<Map<String, String>>) parser.parseMap(json).get("books");
        Map<String, BookFull> bookFullList = new HashMap<>();
        for(Map<String, String> b: books) {
            String isbn = b.get("isbn13");
            if(!repository.existsByIsbn(isbn)) {
                bookFullList.put(isbn, getInfo(isbn));
            }
        }

        if(bookFullList.size() > 0) {

            List<Book> shortBooks = bookFullList.values().stream()
                .map(b -> mapper.convertValue(b, Book.class))
                .collect(Collectors.toList());

            Iterable<Book> all = repository.saveAll(shortBooks);
            for (Book b : all) {
                BookFull bookFull = bookFullList.get(b.getIsbn());
                bookFull.setBookId(b.getId());
                bookFullRepository.save(bookFull);
            }
        }
    }

    private BookFull getInfo(String isbn) {
        String json = itBookstoreService.getJsonInfoByIsbn(isbn);
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> info = parser.parseMap(json);

        BookFull bookFull = new BookFull();
        bookFull.setTitle((String) info.get("title"));
        bookFull.setSubtitle((String) info.get("subtitle"));
        bookFull.setYear(Integer.parseInt((String) info.get("year")));
        bookFull.setAuthor((String) info.get("authors"));
        bookFull.setDescription((String) info.get("desc"));
        bookFull.setIsbn(isbn);

        return bookFull;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    public void synchronize() {
        List<BookFull> books = bookFullRepository.findAll();
        for (BookFull book : books) {
            if (book.getBookId() == null) {
                Book newBook = new Book();
                newBook.setTitle(book.getTitle());
                newBook = repository.save(newBook);
                book.setBookId(newBook.getId());
                bookFullRepository.save(book);
            }
        }
    }
}
