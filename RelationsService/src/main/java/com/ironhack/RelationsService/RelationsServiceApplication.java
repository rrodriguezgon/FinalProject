package com.ironhack.RelationsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RelationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationsServiceApplication.class, args);
	}

}
