package com.blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blackjack.entity.card.Card;
import com.blackjack.entity.game.Game;
import com.blackjack.entity.game.Game_Status;
import com.blackjack.entity.player.Player;
import com.blackjack.entity.room.Room;
import com.blackjack.entity.user.User;
import com.blackjack.exception.GameException;
import com.blackjack.exception.TransactionException;
import com.blackjack.service.GameService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

	@Autowired
	private GameService gameService;
	
	static Player player2 = new Player(new User("p3", "T03"));;
	static Player player1 = new Player(new User("p3", "T03"));
	
	@BeforeClass
	static void init() {
		System.out.println("beforeClass");
		
	}

	@Test
	public void CreateGame() throws GameException {
		Room room = gameService.CreateRoom(player1);
		assertThat(room.getStatus().equals(Game_Status.NEW));
		
	}
	@Test
	public void joinGame() throws GameException {
		Room room1 = gameService.CreateRoom(player1);
		String roomid = room1.getRoomId();
		Room room2 = gameService.joinRoom(roomid, player2);
		
		assertThat(room1.equals(room2));
		assertThat(room2.getStatus().equals(Game_Status.NEW));
	}
	@Test
	public void startGameTest() throws GameException {
		Game_Status rs = Game_Status.IN_PROCESS;
		Room room1 = gameService.CreateRoom(player1);
		String gameid = room1.getRoomId();
		Room room2 = gameService.joinRoom(gameid, player2);
		
		Game start = gameService.startGame(gameid);
		assertThat(start.equals(rs));
		
		Card card = gameService.hitCard(player1.getPlayerId());
		System.out.println(card.getNo()+ " " + card.getSuit());
		assertTrue(card!= null);
	
	}
	@Test
	public void gamePlayTest() throws GameException, TransactionException {
		Room room1 = gameService.CreateRoom(player1);
		String gameid = room1.getRoomId();
		Room room2 = gameService.joinRoom(gameid, player2);
		
		GamePlay gamePlay = new GamePlay(100, gameid);
		
		Game game = gameService.gamePlay(gamePlay);
		
		assertTrue(game!=null);
		
	}
}
