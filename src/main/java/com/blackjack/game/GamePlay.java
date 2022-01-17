package com.blackjack.game;

public class GamePlay {
	private int fare;
	private String gameId;
	private String roomId;


	public GamePlay(int fareOfAmount, String roomId) {
		super();
		this.fare = fareOfAmount;
		this.roomId = roomId;

	}
	public int getFareOfAmount() {
		return fare;
	}

	public void setFareOfAmount(int fareOfAmount) {
		this.fare = fareOfAmount;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	

}
