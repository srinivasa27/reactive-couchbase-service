package com.cb.ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;


@SpringBootApplication
@EnableReactiveCouchbaseRepositories("com.cb.ex.repo")
public class CouchbaseReactiveServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouchbaseReactiveServiceApplication.class, args);
	}

}
