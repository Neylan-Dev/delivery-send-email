package com.neylandev.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DeliverySendEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliverySendEmailApplication.class, args);
	}

}
