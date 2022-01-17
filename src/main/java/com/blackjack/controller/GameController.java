package com.blackjack.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blackjack.entity.player.Player;
import com.blackjack.entity.player.PlayerStorage;
import com.blackjack.entity.user.User;
import com.blackjack.service.UserService;

@Controller
public class GameController {
	@Autowired
	private UserService userService;
	
	private Logger log = LoggerFactory.getLogger(GameController.class);
	
	
	@GetMapping(value = { "/"})
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute User user, Model model) {
		Player player = new Player(userService.getUser(user.getUserName()));
		log.info("get player : {}",player.getPlayerName());
		PlayerStorage.getInstance().addPlayer(player);
		model.addAttribute("player", player);
		model.addAttribute("id", player.getPlayerId());
		return "index";
	}
	
	@PostMapping("/sign-up")
	public String signUp(@ModelAttribute User user) {
		User newUser = userService.createUser(user);
		log.info("Created a new user : {}", newUser);
		return "signUp-success";	
		
	}
	@GetMapping("/game")
	public String game() {
		return "index";
	}
}
