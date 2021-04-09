package com.cattailstore.store.model.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "books")
@Data
@AllArgsConstructor
public class BookFull {

	@Id
	String id;

	Long bookId;

	String title;

	String author;

	String description;
}
