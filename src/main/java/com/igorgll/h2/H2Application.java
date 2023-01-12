package com.igorgll.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.igorgll.h2.Clientes")
public class H2Application {

	public static void main(String[] args) {
		SpringApplication.run(H2Application.class, args);
	}

}
