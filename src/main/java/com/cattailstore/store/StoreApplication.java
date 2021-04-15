package com.cattailstore.store;

import com.cattailstore.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.cattailstore.store.repository.mongodb")
@EnableJpaRepositories(basePackages = "com.cattailstore.store.repository.mysql")
@SpringBootApplication
public class StoreApplication{

	@Autowired
	public BookService service;

	@Autowired
	public Environment env;

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner setupData() {
		return (args) -> service.uploadData();
	}

//	@Override
//	public void run(String... args) {
//		if(!Arrays.asList(env.getActiveProfiles()).contains("test")) {
//			service.uploadData();
//		}
//	}
}
