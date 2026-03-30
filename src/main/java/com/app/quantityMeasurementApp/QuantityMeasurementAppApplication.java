package com.app.quantityMeasurementApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantityMeasurementAppApplication {

	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementAppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(QuantityMeasurementAppApplication.class, args);
	}

}