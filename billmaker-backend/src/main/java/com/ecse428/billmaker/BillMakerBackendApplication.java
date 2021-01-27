package com.ecse428.billmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillMakerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillMakerBackendApplication.class, args);
	}
	@RequestMapping("/")
	public String greeting(){
		return "Hello world!";
	}

}
