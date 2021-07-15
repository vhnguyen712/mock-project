package com.lms.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.lms.commom.entity"})
public class LmsManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsManagerApplication.class, args);
	}

}
