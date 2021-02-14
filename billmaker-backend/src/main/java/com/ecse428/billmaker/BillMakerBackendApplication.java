package com.ecse428.billmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
@EntityScan("com.ecse428.billmaker.model")
@EnableJpaRepositories("com.ecse428.billmaker.dao")
public class BillMakerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillMakerBackendApplication.class, args);
	}
	@RequestMapping("/")
	public String greeting(){
		return "Hello world!";
	}

}
