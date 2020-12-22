package com.demo.batchprocessor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SampleBatchProcessorApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(SampleBatchProcessorApplication.class, args)));
		//SpringApplication.run(SampleBatchProcessorApplication.class, args);
	}

}
