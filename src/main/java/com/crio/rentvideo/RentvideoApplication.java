package com.crio.rentvideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.crio.rentvideo")
public class RentvideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentvideoApplication.class, args);
	}

}
