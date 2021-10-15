package rockPaperScissors.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rockPaperScissors.exception.RockPaperScissorsException;
import rockPaperScissors.model.Game;
import rockPaperScissors.model.GameStatistics;
import rockPaperScissors.model.Hand;
import rockPaperScissors.model.IPlayer;
import rockPaperScissors.model.Round;
import rockPaperScissors.model.RoundResult;

/**
 * Singleton class for managing games
 * @author antosanc
 *
 */
public class GameManager {
	
	private static GameManager instance = null;
	
	private Map<Integer,Game> gameTable;
	
	private GameStatistics statistics;
	
	private int gameIdSequence = 1;
	
	private GameManager() {
		this.gameTable = Collections.synchronizedMap(new HashMap<Integer, Game>());
		this.statistics = new GameStatistics();
	}
	
	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}
	
	private synchronized int getNextId() {
		return gameIdSequence++;
	}
	
	
	/**
	 * Creates a new game
	 * @param player Opponent player 
	 * @return game Id
	 */
	public int createGame(IPlayer player) {
		int gameId = getNextId();
		this.gameTable.put(gameId,  new Game(gameId, player));
		return gameId;
	}
	
	/**
	 * Plays one round of the game
	 * @param gameId Id of the game
	 * @param hand Hand the human player has played
	 * @return result of the round
	 */
	public Round playRound(int gameId, Hand hand) {
		Game game = checkGameId(gameId);
		
		Hand p2Hand = game.getPlayer2().chooseHand();
		RoundResult roundResult = evaluateHands(hand, p2Hand);
		Round result = new Round(hand, p2Hand, roundResult);
		game.getGameRounds().add(result);
		
		if (roundResult == RoundResult.P1WINS) {
			synchronized(this){
				this.statistics.incrementP1Wins();
			}
		} else if (roundResult == RoundResult.P2WINS) {
			synchronized(this){
				this.statistics.incrementP2Wins();
			}
		} else {
			synchronized(this){
				this.statistics.incrementDraws();
			}
		}

		return result;
	}
	
	/**
	 * Chooses the result of a round
	 * @param p1Hand Player 1 Hand
	 * @param p2Hand Player 2 Hand
	 * @return RoundResult object that represents the round result
	 */
	private RoundResult evaluateHands(Hand p1Hand, Hand p2Hand) {
		RoundResult result = RoundResult.DRAW;
		if (p1Hand != p2Hand) {
			if((p1Hand == Hand.PAPER && p2Hand == Hand.ROCK) || (p1Hand == Hand.ROCK && p2Hand == Hand.SCISSORS) ||  (p1Hand == Hand.SCISSORS && p2Hand == Hand.PAPER)) {
				result = RoundResult.P1WINS;
			} else {
				result = RoundResult.P2WINS;
			}
			
		}
		return result;
	}
	
	/**
	 * Returns the current rounds of a given game
	 * @param gameId Id of the game
	 * @return List of rounds
	 */
	public List<Round> getGameRounds(int gameId) {
		Game game = checkGameId(gameId);

		return game.getGameRounds();
	}
	
	/**
	 * Restarts the given game
	 * @param gameId id of the game to restart
	 */
	public void restartGame(int gameId) {
		Game game = checkGameId(gameId);
		game.getGameRounds().clear();
		
	}
	
	/**
	 * Checks that game id exists and if so returns given game object
	 * @param gameId Id of the game
	 * @return Game
	 */
	private Game checkGameId(int gameId) {
		Game game = this.gameTable.get(gameId);		
		if (game == null) {
			throw new RockPaperScissorsException("Invalid game id");
		}
		return game;
		
	}
	
	/**
	 * Returns general statistics
	 * @return statistics
	 */
	public GameStatistics getStatistics() {
		return this.statistics;
	}

	

}
