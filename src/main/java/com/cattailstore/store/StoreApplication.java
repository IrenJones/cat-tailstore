package com.cattailstore.store;

import java.io.IOException;
import com.cattailstore.store.repository.mongodb.BookFullRepository;
import com.cattailstore.store.service.BookService;
import com.google.gdata.util.ServiceException;
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
	public BookService service;

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}


	@Override
	public void run(String... args) {
		service.uploadData();
	}
}
