package com.blackjack.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.entity.card.Card;
import com.blackjack.entity.game.Game;
import com.blackjack.entity.game.GameStorage;
import com.blackjack.entity.game.Game_Status;
import com.blackjack.entity.game.HandCardsStorage;
import com.blackjack.entity.player.GameRole;
import com.blackjack.entity.player.Player;
import com.blackjack.entity.room.Room;
import com.blackjack.entity.room.RoomStorage;
import com.blackjack.entity.room.Room_Status;
import com.blackjack.exception.GameException;
import com.blackjack.exception.TransactionException;
import com.blackjack.game.GamePlay;
import com.blackjack.game.GameProcess;
import com.blackjack.game.GameResult;

@Service
public class GameService {
	private Logger log = LoggerFactory.getLogger(GameService.class);
	@Autowired
	private GameProcess gameProcess;

	@Autowired

	public Room CreateRoom(Player player) {
		Room room = new Room();
		room.addPlayer(player);
		room.setRoomId("" + new Random().nextInt(1000));
		room.setStatus(Room_Status.JOINABLE);
		player.setGameRole(GameRole.MASTER);
		RoomStorage.getInstance().setRoom(room);
		return room;

	}
	public Room getRoom(String roomId) {
		return RoomStorage.getInstance().getRooms().get(roomId);
	}

	public Room joinRoom(String roomId, Player player) throws GameException {
		if (!RoomStorage.getInstance().isExist(roomId)) {
			log.info("Room is not exist : {}", roomId);
			throw new GameException("Room doesn't exits");
		}
		Room room = RoomStorage.getInstance().getRooms().get(roomId);
		if (room.getStatus() == Room_Status.FULL) {
			throw new GameException("Room is full");
		}
		if (room.getPlayers().size() == 4) {
			room.setStatus(Room_Status.FULL);
		}
		player.setGameRole(GameRole.JOINER);
		room.addPlayer(player);
		room.setStatus(Room_Status.WAIT);
		RoomStorage.getInstance().setRoom(room);

		return room;
	}

	public Game startGame(String roomId) throws GameException {
		Game game = new Game();
		game.setGameId(UUID.randomUUID()+"");
		game.setRoomId(roomId);
		ArrayList<Player> players = RoomStorage.getInstance().getRooms().get(roomId).getPlayers();

		// Create init 2 cards to start game
		players.forEach(p -> {
			try {
				gameProcess.startOfCards(p);
			} catch (GameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		game.setPlayersCards( HandCardsStorage.getInstance().getCardStorage());
		game.setStatus(Game_Status.IN_PROCESS);
		GameStorage.getInstance().setGame(game);
		return game;

	}

	public Card hitCard(String playerId) {
		Card card = gameProcess.hitCard();
		HandCardsStorage.getInstance().getHandCards(playerId).add(card);
		return card;
	}

	public Game gamePlay(GamePlay gamePlay) throws GameException, TransactionException {
		if (!GameStorage.getInstance().isExist(gamePlay.getGameId())) {
			throw new GameException("Game not found!");
		}
		Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());
		if (game.getStatus().equals(Game_Status.FINISH)) {
			throw new GameException("Game is already finish");

		}
		ArrayList<Player> players = RoomStorage.getInstance().getRooms().get(gamePlay.getRoomId()).getPlayers();
		Player master = getMaster(players);
		players.forEach(p -> {
			if (!p.getGameRole().equals(GameRole.MASTER)) {
				GameResult competeResult;
				try {
					competeResult = gameProcess.compete(master, p, gamePlay.getFareOfAmount());
					game.getResult().add(competeResult);
				} catch (TransactionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		game.setStatus(Game_Status.FINISH);
		GameStorage.getInstance().setGame(game);
		return game;
	}

	private Player getMaster(ArrayList<Player> players) {
		Player mst = null;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player player = (Player) it.next();
			if (player.getGameRole().equals(GameRole.MASTER)) {
				mst = player;
				break;
			}
		}
		return mst;
	}

}
