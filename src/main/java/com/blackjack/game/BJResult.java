package com.blackjack.game;

public enum BJResult {
	BAN_BAN(21), BAN_LUCK(21), BUSTS(23), MASTER_OF_FREE_HAND(0), JOINER_OF_FREE_HAND(0), FIVE_DRAGON(1);

	private int value;

	private BJResult(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
