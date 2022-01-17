package com.blackjack.game;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.blackjack.entity.card.Card;
import com.blackjack.entity.card.No;
@Component
public final class GameRules {
	public static boolean isBanLuck(ArrayList<Card> cards) {
		boolean isBanLuck = false;
		if (isHasAce(cards) && cards.size() == 2) {
			for (Card card : cards) {
				if (card.getNo().getValues() >= 10) {
					isBanLuck = true;

				}
			}
		}
		return isBanLuck;
	}

	// Ace + Ace = ban-ban
	public static boolean isBanBan(ArrayList<Card> handCards) {
		boolean isBanBan = false;
		if (handCards.size() == 2) {
			if (handCards.get(0).getNo().equals(No.ACE) && handCards.get(1).getNo().equals(No.ACE)) {
				isBanBan = true;
			}
		}
		return isBanBan;

	}

	public static boolean isFiveDragonHands(ArrayList<Card> currentCards) {
		return (currentCards.size() == 5 && totalPoint(currentCards) <= 21) ? true : false;

	}

	// total > 21, busts.
	public static boolean isBust(ArrayList<Card> handCards) {
		return totalPoint(handCards) > 21 ? true : false;
	}

	public static int totalPoint(ArrayList<Card> cards) {
		int total = 0;

		if (isHasAce(cards)) {
			total = isHasAcePoint(cards);
		} else {
			for (Card card : cards) {
				if (card.getNo().getValues() > 10) {
					total += 10;
				} else {
					total += card.getNo().getValues();
				}

			}
		}
		return total;

	}

	private static int isHasAcePoint(ArrayList<Card> cards) {
		int subTotal = 0;
		int aceCount = 0;
		int ace_point = 1;
		for (Card card : cards) {
			if (!card.getNo().equals(No.ACE)) {
				if (card.getNo().getValues() > 10) {
					subTotal += 10;
				} else {
					subTotal += card.getNo().getValues();
				}
			} else if (card.getNo().equals(No.ACE)) {
				aceCount++;
				if (cards.size() == 2) {
					ace_point = 11;
				} else if (cards.size() == 3 && aceCount >= 1) {
					ace_point = 10;
				} else {
					ace_point = 1;
				}
				subTotal += ace_point;

			}
			if (subTotal > 21 && aceCount >= 1 && cards.size() <= 3) {
				subTotal = subTotal - aceCount * 10 + aceCount;

			}
		}
		return subTotal;

	}

	private static boolean isHasAce(ArrayList<Card> cards) {
		boolean hasAce = false;
		for (Card card : cards) {
			if (card.getNo().equals(No.ACE)) {
				hasAce = true;
			}
		}
		return hasAce;
	}

}
