package com.blackjack.entity.player;

import java.util.HashMap;
import java.util.Map;

public class PlayerStorage {
	private Map<String, Player> players;
	private static PlayerStorage instance;

	private PlayerStorage() {
		players = new HashMap<String, Player>();
	}

	public synchronized static PlayerStorage getInstance() {
		if (instance == null) {
			instance = new PlayerStorage();
		}
		return instance;
	}

	public Map<String, Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}
	public boolean isExist(String id) {
		return players.containsKey(id);
	}

	public Player getPlayer(String playerId) {
		return players.get(playerId);
	}
}
