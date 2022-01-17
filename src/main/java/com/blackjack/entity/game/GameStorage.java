package com.blackjack.entity.game;

import java.util.HashMap;
import java.util.Map;

public class GameStorage {
	private Map<String, Game> games;
	private static GameStorage instance;

	private GameStorage() {
		games = new HashMap<String, Game>();
	}

	public synchronized static GameStorage getInstance() {
		if (instance == null) {
			instance = new GameStorage();
		}
		return instance;
	}

	public Map<String, Game> getGames() {
		return games;
	}

	public void setGame(Game game) {
		games.put(game.getGameId(), game);
	}
	public boolean isExist(String id) {
		return games.containsKey(id);
	}
}
