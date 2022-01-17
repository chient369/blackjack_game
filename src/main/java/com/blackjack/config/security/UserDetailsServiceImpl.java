package com.blackjack.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blackjack.entity.user.User;
import com.blackjack.entity.user.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.getUser(username);
		if (user == null) throw new UsernameNotFoundException("user isn't invailid");
		
		return new MyUserDetails(user);
	}

}
