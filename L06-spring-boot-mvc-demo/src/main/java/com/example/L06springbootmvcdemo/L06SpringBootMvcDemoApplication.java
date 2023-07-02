package com.example.L06springbootmvcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class L06SpringBootMvcDemoApplication {


	private static Logger LOGGER = LoggerFactory.getLogger(L06SpringBootMvcDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(L06SpringBootMvcDemoApplication.class, args);
		LOGGER.error("This in error log");
		LOGGER.warn("This in warn log");
		LOGGER.info("This in info log");
		LOGGER.debug("This in debug log");
		LOGGER.trace("This in trace log");
	}

}
