package com.david.MailTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableAsync
public class MailTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailTestApplication.class, args);
	}

}
