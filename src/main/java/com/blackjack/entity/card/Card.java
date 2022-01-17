package com.blackjack.entity.card;

import org.springframework.stereotype.Component;

@Component
public class Card {

	private No no;
	private Suit suit;

	public Card() {
	}

	public Card(No no, Suit suit) {
		super();
		this.no = no;
		this.suit = suit;
	}

	public No getNo() {
		return no;
	}

	public void setNo(No no) {
		this.no = no;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

}
