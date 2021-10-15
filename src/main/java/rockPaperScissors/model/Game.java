package rockPaperScissors.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of rounds played by an user
 * @author antosanc
 *
 */
public class Game {

	private int gameId;
	private IPlayer player2;
	private List<Round> gameRounds;
	
	public Game(int id) {
		this.gameId = id;
	}
	
	public Game(int id, IPlayer player2ToSet) {
		this.gameId = id;
		this.player2 = player2ToSet;
		this.gameRounds = new ArrayList<Round>();
	}
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public IPlayer getPlayer2() {
		return player2;
	}
	public void setPlayer2(IPlayer player2) {
		this.player2 = player2;
	}
	public List<Round> getGameRounds() {
		return gameRounds;
	}
	public void setGameRounds(List<Round> gameRounds) {
		this.gameRounds = gameRounds;
	}
	
}
