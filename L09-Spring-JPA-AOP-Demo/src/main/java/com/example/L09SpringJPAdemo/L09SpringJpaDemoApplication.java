package com.example.L09SpringJPAdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class L09SpringJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(L09SpringJpaDemoApplication.class, args);
	}

}
