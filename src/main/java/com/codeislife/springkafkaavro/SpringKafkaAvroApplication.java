package com.codeislife.springkafkaavro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SpringKafkaAvroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaAvroApplication.class, args);
	}

}
