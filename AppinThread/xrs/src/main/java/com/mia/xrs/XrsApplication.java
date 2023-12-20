package com.mia.xrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class XrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(XrsApplication.class, args);
	}

}
