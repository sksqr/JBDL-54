package com.example.L08SpringJPAdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class L08SpringJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(L08SpringJpaDemoApplication.class, args);
	}

}
