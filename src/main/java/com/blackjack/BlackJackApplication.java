package com.blackjack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.blackjack" })
public class BlackJackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackJackApplication.class, args);

	}

}
