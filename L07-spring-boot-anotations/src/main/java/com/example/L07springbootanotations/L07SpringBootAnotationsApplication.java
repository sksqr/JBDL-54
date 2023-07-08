package com.example.L07springbootanotations;

import org.gfg.analyzer.KeywordAnalyzerImpl;
import org.gfg.analyzer.KeywordAnalyzerInterface;
import org.gfg.analyzer.UniqueKeywordAnalyzerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class L07SpringBootAnotationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(L07SpringBootAnotationsApplication.class, args);
	}

}
