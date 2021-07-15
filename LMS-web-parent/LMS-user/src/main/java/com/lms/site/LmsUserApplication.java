package com.lms.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.lms.commom.entity"})
public class LmsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsUserApplication.class, args);
	}

}
