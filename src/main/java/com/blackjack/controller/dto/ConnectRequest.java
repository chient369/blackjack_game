package com.blackjack.controller.dto;

import org.springframework.stereotype.Component;

import com.blackjack.entity.player.Player;

@Component
public class ConnectRequest {
	private String roomId;
	private Player player;

	public ConnectRequest() {
	}
	public ConnectRequest(String roomId) {
		super();
		this.roomId = roomId;
	}
	public ConnectRequest(Player player, String roomId) {
		super();
		this.player = player;
		this.roomId = roomId;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setGameId(String roomId) {
		this.roomId = roomId;
	}

}
