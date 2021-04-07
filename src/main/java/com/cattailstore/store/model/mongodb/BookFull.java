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
	public String id;

	public Long bookId;

	public String title;

	public String description;
}
