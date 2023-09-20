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

//	@Bean
//	CommandLineRunner commandLineRunner(S3Service s3Service) {
//		return args -> {
//			s3Service.putObject("flexcoreobjectstore",
//					"foo",
//					"hello world".getBytes());
//
//			byte[] foos = s3Service.getObject("flexcoreobjectstore", "foo");
//
//			System.out.println(new String(foos));
//		};
//	}

}
