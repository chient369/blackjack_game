package com.blackjack.entity.card;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class CardStorage {
	private ArrayList<Card> cardStorage;
//	private static CardStorage INSTANCE;
	
	public CardStorage() {
//		INSTANCE = new CardStorage();
		cardStorage = new ArrayList<Card>();
		Suit[] suits = Suit.values();
		No[] no = No.values();
		for (int i = 0; i < no.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				cardStorage.add(new Card(no[i], suits[j]));
			}
		}

	}
//	public synchronized static CardStorage getInsance() {
//		if (INSTANCE == null) {
//			INSTANCE = new CardStorage();			
//		}
//		return INSTANCE;
//		
//	}
	public ArrayList<Card> getCardStorage() {
		return cardStorage;
	}
	public void setCardStorage(ArrayList<Card> cardStorage) {
		this.cardStorage = cardStorage;
	}

}
