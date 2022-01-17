package com.blackjack.entity.player;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.blackjack.entity.user.User;

@Component
public class Player {
	private User user;
	private String playerName;
	private String playerId;
	private GameRole gameRole;

	public Player() {
		super();

	}

	public Player(User user) {
		this.setUser(user);
		this.playerName = user.getUserName();
		this.playerId = UUID.randomUUID().toString();
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public GameRole getGameRole() {
		return gameRole;
	}

	public void setGameRole(GameRole gameRole) {
		this.gameRole = gameRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getPlayerName() {
		return this.playerName;
	}
	public int getWallet() {
		return this.user.getWallet();
	}

	public void setWallet(int wallet) {
		this.user.setWallet(wallet);;
	}

	public int getWalet() {
		return user.getWallet();
	}

	

}
