package com.blackjack.entity.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.blackjack.entity.card.Card;
import com.blackjack.exception.GameException;

public class HandCardsStorage {
	private  Map<String, ArrayList<Card>> cardStorage;
	private  static HandCardsStorage INSTANCE;
	private HandCardsStorage() {
		this.cardStorage = new HashMap<String, ArrayList<Card>>();
	}
	public static synchronized HandCardsStorage getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HandCardsStorage();			
		}
		return INSTANCE;
	}
	public ArrayList<Card> getHandCards(String playerId){
		return cardStorage.get(playerId);
	}
	public void addHandCard(String playerId, ArrayList<Card> hands) throws GameException {
		if (cardStorage.containsKey(playerId)) {
			throw new GameException("Player is already exits");	
		}
		cardStorage.put(playerId, hands);
	}
	public void clearGame() {
		if (!cardStorage.isEmpty()) {
			cardStorage.clear();
		}
	}
	public Map<String, ArrayList<Card>> getCardStorage() {
		return cardStorage;
	}
	
	
	

}
