package com.blackjack.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blackjack.entity.user.User;
import com.blackjack.entity.user.UserRepository;

@Service
public class UserService {
	
	
	private Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRespository;
	
	public User createUser(User u) {
		log.info("Created a new user : {}", u);
		User user = new User(u.getUserName(), u.getPassword());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setRole("user");
		User newUser =  userRespository.save(user);	
		return newUser;
	}
	public User getUser(String username) {
		User user = userRespository.getUser(username);
		return user;
	}
	

}
