package com.blackjack.game;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blackjack.entity.card.Card;
import com.blackjack.entity.card.CardStorage;
import com.blackjack.entity.game.HandCardsStorage;
import com.blackjack.entity.player.GameRole;
import com.blackjack.entity.player.Player;
import com.blackjack.exception.GameException;
import com.blackjack.exception.TransactionException;
import com.blackjack.service.Transaction;

@Component
public class GameProcess {
	
	@Autowired
	GameRules gameRules;

	@Autowired
	CardStorage cardStorage;
	public Card hitCard() {
		ArrayList<Card> cards = cardStorage.getCardStorage();
		Random rand = new Random();
		int cards_length = cards.size();
		Card card = cards.get(rand.nextInt(cards_length));
		cards.remove(card);
		cards_length--;
		if (cards_length < 1) {
			throw new IllegalArgumentException("the deck is over");
		}
		System.err.println("Card storage : "+cards_length);
		return card;

	}

	public void startOfCards(Player player) throws GameException {
		ArrayList<Card> handCards = new ArrayList<Card>();
		handCards.add(hitCard());
		handCards.add(hitCard());
		HandCardsStorage.getInstance().addHandCard(player.getPlayerId(), handCards);
	}

	public  BJResult getResult(Player player) {
		ArrayList<Card> cards = HandCardsStorage.getInstance().getHandCards(player.getPlayerId());
		BJResult result = null;
		if (GameRules.isBanBan(cards)) {
			result = BJResult.BAN_BAN;
		} else if (GameRules.isBanLuck(cards)) {
			result = BJResult.BAN_LUCK;

		} else if (GameRules.isFiveDragonHands(cards)) {
			result = BJResult.FIVE_DRAGON;
		} else {
			int points = GameRules.totalPoint(cards);
			if (player.getGameRole() == GameRole.MASTER) {
				result = BJResult.MASTER_OF_FREE_HAND;
				result.setValue(points);
			} else {
				result = BJResult.JOINER_OF_FREE_HAND;
				result.setValue(points);
			}
		}
		return result;
	}

	public GameResult compete(Player master, Player joiner, int fare) throws TransactionException {
		GameResult result = new GameResult(master,joiner,fare); 
		BJResult masterResult = getResult(master);
		BJResult joinerResult = getResult(joiner);
		switch (masterResult) {
		case FIVE_DRAGON:
			if (joinerResult == BJResult.FIVE_DRAGON) {
				result.setResult(Game_Result.DRAWN);
			}
			Transaction.transfers(joiner, master, fare);
			result.setResult(Game_Result.MASTER_WIN);
			if (joinerResult == BJResult.FIVE_DRAGON) {
				Transaction.transfers(master, joiner, fare);
				result.setResult(Game_Result.JOINER_WIN);
			}
			break;
		case BAN_BAN:
			if (joinerResult == BJResult.BAN_BAN) {
				result.setResult(Game_Result.DRAWN);
			}

			Transaction.transfers(joiner, master, fare);
			result.setResult(Game_Result.MASTER_WIN);

			if (joinerResult == BJResult.BAN_BAN) {
				Transaction.transfers(master, joiner, fare);
				result.setResult(Game_Result.JOINER_WIN);
			}
			break;
		case BAN_LUCK:
			if (joinerResult == BJResult.BAN_LUCK) {
				result.setResult(Game_Result.DRAWN);
			}
			Transaction.transfers(joiner, master, fare);
			result.setResult(Game_Result.MASTER_WIN);

			if (joinerResult == BJResult.BAN_LUCK) {
				Transaction.transfers(master, joiner, fare);
				result.setResult(Game_Result.JOINER_WIN);
			}
			break;
		case BUSTS:
			if (joinerResult == BJResult.BUSTS) {
				result.setResult(Game_Result.DRAWN);
			} else {
				Transaction.transfers(master, joiner, fare);
				result.setResult(Game_Result.JOINER_WIN);
			}
			if (joinerResult == BJResult.BUSTS) {
				Transaction.transfers(joiner, master, fare);
				result.setResult(Game_Result.MASTER_WIN);
			}
			break;
		case MASTER_OF_FREE_HAND:
			int joinerPoints = masterResult.getValue();
			int masterPoints = joinerResult.getValue();
			if (joinerPoints == masterPoints)
				result.setResult(Game_Result.DRAWN);
			if (joinerPoints > masterPoints) {
				Transaction.transfers(master, joiner, fare);
				result.setResult(Game_Result.JOINER_WIN);
			} else {
				Transaction.transfers(joiner, master, fare);
				result.setResult(Game_Result.MASTER_WIN);

			}
		default:
			break;
		}
		return result;
	}
}
