package com.blackjack;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Passs {
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("111111"));
	}

}
