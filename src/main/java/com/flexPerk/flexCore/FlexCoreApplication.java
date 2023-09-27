package com.flexPerk.flexCore;

import com.flexPerk.flexCore.service.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlexCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexCoreApplication.class, args);
	}

}
