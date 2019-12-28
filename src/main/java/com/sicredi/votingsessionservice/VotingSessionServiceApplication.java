package com.sicredi.votingsessionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class VotingSessionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingSessionServiceApplication.class, args);
	}
}
