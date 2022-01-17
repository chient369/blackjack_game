package com.blackjack.entity.card;

public enum No {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(2), NINE(9), TEN(10), JACK(11), QUEEN(12),
	KING(13);

	private int values;

	private No(int i) {

	}

	public int getValues() {
		return values;
	}

	public void setValues(int values) {
		this.values = values;
	}

}
