package com.cattailstore.store.model.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFull {

	@Id
	String id;

	Long bookId;

	String title;

	String subtitle;

	String author;

	int year;

	String description;

	String isbn;
}
