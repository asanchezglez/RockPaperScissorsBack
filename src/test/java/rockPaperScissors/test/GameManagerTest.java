package rockPaperScissors.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.databind.ObjectMapper;

import rockPaperScissors.controller.GameController;
import rockPaperScissors.manager.GameManager;
import rockPaperScissors.model.GameStatistics;
import rockPaperScissors.model.Hand;
import rockPaperScissors.model.IPlayer;
import rockPaperScissors.model.RandomPlayer;
import rockPaperScissors.model.RockPlayer;
import rockPaperScissors.model.Round;
import rockPaperScissors.model.RoundResult;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameManagerTest {
	
	static GameManager gManager; 
	static int game1Id, game2Id, game3Id;
	ObjectMapper jsonParser = new ObjectMapper();

	
	@Before
	public void setupTest() {
		gManager = GameManager.getInstance();
		
		IPlayer rockPlayer = new RockPlayer();
		game1Id = gManager.createGame(rockPlayer);
		
		IPlayer randomPlayer = new RandomPlayer();
		game2Id = gManager.createGame(randomPlayer);
		
		IPlayer rockPlayer2 = new RockPlayer();
		game3Id = gManager.createGame(rockPlayer2);
		
		
	}
	
	@Test
	public void assertStatisticsTest() {
		int rounds = 0;
		int p1Wins = 0;
		int p2Wins = 0;
		int draws = 0;
		GameStatistics stats = gManager.getStatistics();
		assertEquals(stats.getDraws(),draws);
		assertEquals(stats.getP1Wins(),p1Wins);
		assertEquals(stats.getP2Wins(),p2Wins);
		assertEquals(stats.getRounds(),rounds);
		
		gManager.playRound(game1Id, Hand.PAPER);
		assertEquals(stats.getDraws(),draws);
		assertEquals(stats.getP1Wins(),++p1Wins);
		assertEquals(stats.getP2Wins(),p2Wins);
		assertEquals(stats.getRounds(),++rounds);
		
		gManager.playRound(game1Id, Hand.ROCK);
		assertEquals(stats.getDraws(),++draws);
		assertEquals(stats.getP1Wins(),p1Wins);
		assertEquals(stats.getP2Wins(),p2Wins);
		assertEquals(stats.getRounds(),++rounds);
		
		gManager.playRound(game1Id, Hand.SCISSORS);
		assertEquals(stats.getDraws(),draws);
		assertEquals(stats.getP1Wins(),p1Wins);
		assertEquals(stats.getP2Wins(),++p2Wins);
		assertEquals(stats.getRounds(),++rounds);
		
		gManager.playRound(game1Id, Hand.PAPER);
		assertEquals(stats.getDraws(),draws);
		assertEquals(stats.getP1Wins(),++p1Wins);
		assertEquals(stats.getP2Wins(),p2Wins);
		assertEquals(stats.getRounds(),++rounds);
		
		gManager.playRound(game3Id, Hand.PAPER);
		assertEquals(stats.getDraws(),draws);
		assertEquals(stats.getP1Wins(),++p1Wins);
		assertEquals(stats.getP2Wins(),p2Wins);
		assertEquals(stats.getRounds(),++rounds);
		
		gManager.playRound(game3Id, Hand.SCISSORS);
		assertEquals(stats.getDraws(),draws);
		assertEquals(stats.getP1Wins(),p1Wins);
		assertEquals(stats.getP2Wins(),++p2Wins);
		assertEquals(stats.getRounds(),++rounds);
		
		gManager.playRound(game2Id, Hand.ROCK); //Random
		assertEquals(stats.getRounds(), ++rounds);
		assertEquals(stats.getDraws()+stats.getP1Wins()+stats.getP2Wins(), draws+p1Wins+p2Wins+1);
		
	}
	
	@Test
	public void playAndGetGameRoundsTest() {
		int roundsNum = 0;
		List<Round> stats = gManager.getGameRounds(game1Id);
		assertTrue(stats.isEmpty());
		
		gManager.playRound(game1Id, Hand.PAPER);
		stats = gManager.getGameRounds(game1Id);
		Round lastRound = stats.get(roundsNum);
		assertEquals(stats.size(), ++roundsNum);
		assertEquals(lastRound.getP1Hand(),Hand.PAPER);
		assertEquals(lastRound.getP2Hand(),Hand.ROCK);
		assertEquals(lastRound.getResult(),RoundResult.P1WINS);
		
		gManager.playRound(game1Id, Hand.ROCK);
		stats = gManager.getGameRounds(game1Id);
		lastRound = stats.get(roundsNum);
		assertEquals(stats.size(), ++roundsNum);
		assertEquals(lastRound.getP1Hand(),Hand.ROCK);
		assertEquals(lastRound.getP2Hand(),Hand.ROCK);
		assertEquals(lastRound.getResult(),RoundResult.DRAW);
		
		gManager.playRound(game1Id, Hand.SCISSORS);
		stats = gManager.getGameRounds(game1Id);
		lastRound = stats.get(roundsNum);
		assertEquals(stats.size(), ++roundsNum);
		assertEquals(lastRound.getP1Hand(),Hand.SCISSORS);
		assertEquals(lastRound.getP2Hand(),Hand.ROCK);
		assertEquals(lastRound.getResult(),RoundResult.P2WINS);
		
		gManager.playRound(game2Id, Hand.ROCK);//Other game round does not affect
		stats = gManager.getGameRounds(game1Id);
		assertEquals(stats.size(), roundsNum);
		roundsNum = 0;
		stats = gManager.getGameRounds(game2Id);
		lastRound = stats.get(roundsNum);
		assertEquals(stats.size(), ++roundsNum);
		assertEquals(lastRound.getP1Hand(),Hand.ROCK);
		
		
		
	}
	
	@Test
	public void restartGame() {
		int rounds = 0;
		int p1Wins = 0;
		int p2Wins = 0;
		int draws = 0;
		

		
		gManager.playRound(game1Id, Hand.PAPER);

		
		gManager.playRound(game1Id, Hand.ROCK);

		
		gManager.playRound(game1Id, Hand.SCISSORS);

		
		gManager.playRound(game1Id, Hand.PAPER);

		
		gManager.playRound(game3Id, Hand.PAPER);

		
		gManager.playRound(game3Id, Hand.SCISSORS);
		
		GameStatistics stats = gManager.getStatistics();

		rounds = stats.getRounds();
		draws = stats.getDraws();
		p1Wins = stats.getP1Wins();
		p2Wins = stats.getP2Wins();
		
		gManager.restartGame(game1Id);
		List<Round> gameRounds = gManager.getGameRounds(game1Id);
		assertTrue(gameRounds.isEmpty());
		gameRounds = gManager.getGameRounds(game3Id);
		assertFalse(gameRounds.isEmpty());
		
		stats = gManager.getStatistics();
		assertEquals(stats.getP1Wins(),p1Wins);
		assertEquals(stats.getP2Wins(), p2Wins);
		assertEquals(stats.getDraws(), draws);
		assertEquals(stats.getRounds(),rounds);
		
		
		
	}
	
	@Test
	public void createGameTest() {
		IPlayer rockPlayer = new RockPlayer();
		int newgameId = gManager.createGame(rockPlayer);
		assertEquals(game3Id +1, newgameId);
		List<Round> rounds = gManager.getGameRounds(newgameId);
		assertTrue(rounds.isEmpty());
	}
	
	@Test
	public void exceptionTest() {
		 try {
			 gManager.getGameRounds(1008);
			 fail();
		 } catch (Exception ex) {
			 
		 }
		 
		 try {
			 gManager.restartGame(1008);
			 fail();
		 } catch (Exception ex) {
			 
		 }
	}
	
	
	@Test
	public void generalTest() {
		
	}
	
}
