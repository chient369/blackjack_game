package com.blackjack.entity.room;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.blackjack.entity.player.Player;
@Component
public class Room {

	private String roomId;
	private ArrayList<Player> players;
	private Room_Status status;

	public Room() {
		players = new ArrayList<Player>();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		 players.add(player);
	}
     
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Room_Status getStatus() {
		return status;
	}

	public void setStatus(Room_Status status) {
		this.status = status;
	}

}
