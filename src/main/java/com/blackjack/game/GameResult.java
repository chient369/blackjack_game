package com.blackjack.game;

import org.springframework.stereotype.Component;

import com.blackjack.entity.player.Player;

@Component
public class GameResult {
	private Player master;
	private Player joiner;
	private Game_Result result;
	private int fare;

	public GameResult() {
	}

	public GameResult(Player master, Player joiner, int fare) {
		super();
		this.master = master;
		this.joiner = joiner;
		this.fare = fare;
	}

	public Player getMaster() {
		return master;
	}

	public void setMaster(Player master) {
		this.master = master;
	}

	public Player getJoiner() {
		return joiner;
	}

	public void setJoiner(Player joiner) {
		this.joiner = joiner;
	}

	public Game_Result getResult() {
		return result;
	}

	public void setResult(Game_Result result) {
		this.result = result;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	

}
