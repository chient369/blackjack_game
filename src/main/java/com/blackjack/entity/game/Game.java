package com.blackjack.entity.game;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.blackjack.entity.card.Card;
import com.blackjack.game.GameResult;

@Component
public class Game {
	private String gameId;
	private String roomId;
	private Game_Status status;
	private Map<String, ArrayList<Card>> playersCards ;
	private ArrayList<GameResult> result ;
	public Game() {
		result =  new ArrayList<GameResult>();
	}


	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Game_Status getStatus() {
		return status;
	}

	public void setStatus(Game_Status status) {
		this.status = status;
	}

	public ArrayList<GameResult> getResult() {
		return result;
	}

	public void setResult(ArrayList<GameResult> result) {
		this.result = result;
	}
	
	public Map<String, ArrayList<Card>> getPlayersCards() {
		return playersCards;
	}


	public void setPlayersCards(Map<String, ArrayList<Card>> playersCards) {
		this.playersCards = playersCards;
	}
	


	

}
