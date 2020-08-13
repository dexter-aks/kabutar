package com.dexter.kabutar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = "com.dexter.kabutar.dao")
public class KabutarApplication {

	public static void main(String[] args) {
		SpringApplication.run(KabutarApplication.class, args);
	}

}
