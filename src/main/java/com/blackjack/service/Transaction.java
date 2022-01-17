package com.blackjack.service;

import com.blackjack.entity.player.Player;
import com.blackjack.exception.TransactionException;

public final class Transaction {
	public static synchronized void transfers(Player from, Player to, int money) throws TransactionException {
		if (from.getWallet() < money) {
			throw new TransactionException("Master is not enough money");
		}
		from.setWallet(from.getWallet() - money);
		to.setWallet(to.getWallet() + money);

	}

}
