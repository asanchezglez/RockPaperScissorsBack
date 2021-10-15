package rockPaperScissors.model;

/**
 * Class that represests the general statistics of the game
 * @author antosanc
 *
 */
public class GameStatistics {
	
	private int rounds = 0;
	private int p1Wins = 0;
	private int p2Wins = 0;
	private int draws = 0;
	
	public int getRounds() {
		return rounds;
	}
	public int getP1Wins() {
		return p1Wins;
	}
	public int getP2Wins() {
		return p2Wins;
	}
	public int getDraws() {
		return draws;
	}
	
	public void incrementP1Wins() {
		this.p1Wins++;
		this.rounds++;
	}
	
	public void incrementP2Wins() {
		this.p2Wins++;
		this.rounds++;
	}
	
	public void incrementDraws() {
		this.draws++;
		this.rounds++;
	}

}
