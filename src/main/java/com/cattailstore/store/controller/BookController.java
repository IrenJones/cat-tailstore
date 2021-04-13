package com.cattailstore.store.controller;

import com.cattailstore.store.dto.FullBookDto;
import com.cattailstore.store.dto.UpdateInfo;
import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.model.mysql.Book;
import com.cattailstore.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	public BookService bookService;

	@GetMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable long id) {
		return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
	}

	@GetMapping("/{id}/description")
	public ResponseEntity<BookFull> findByIdFullInfo(@PathVariable long id) {
		return new ResponseEntity<>(bookService.getBookWithDescriptionById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Collection<BookFull>> findBooks() {
		return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<FullBookDto> updateBook(@PathVariable("id") final long bookId,
		@Validated(UpdateInfo.class) @RequestBody final FullBookDto book) {

		return new ResponseEntity<>(bookService.update(bookId, book), HttpStatus.CREATED);
	}

	@PostMapping("/admin/synchronize")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<HttpStatus> synchronize(){
		bookService.synchronize();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
