package com.stock.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockManagementServiceApplication.class, args);
		System.out.println("Hellow from Stock...");
	}

}
