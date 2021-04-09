package com.cattailstore.store;

import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.repository.mongodb.BookFullRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.cattailstore.store.repository.mongodb")
@EnableJpaRepositories(basePackages = "com.cattailstore.store.repository.mysql")
@SpringBootApplication
public class StoreApplication implements CommandLineRunner {

	@Autowired
	public BookFullRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}


	@Override
	public void run(String... args) {
		repository.save(new BookFull("1", null, "Toto", "ogogo", "Da"));

		//BookFull book = repository.findByBookId(2L);
		//System.out.println(book.getTitle());
	}
}
