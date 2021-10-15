package rockPaperScissors.model;

/**
 * Round of the game
 * @author antosanc
 *
 */
public class Round {
	/**
	 * Player 1 Hand
	 */
	private Hand p1Hand;
	/**
	 * Player 2 Hand
	 */
	private Hand p2Hand;
	/**
	 * Result of the round
	 */
	private RoundResult result;
	
	public Round(Hand p1Hand, Hand p2Hand, RoundResult result) {
		this.p1Hand = p1Hand;
		this.p2Hand = p2Hand;
		this.result = result;
	}

	public Hand getP1Hand() {
		return p1Hand;
	}

	public void setP1Hand(Hand p1Hand) {
		this.p1Hand = p1Hand;
	}

	public Hand getP2Hand() {
		return p2Hand;
	}

	public void setP2Hand(Hand p2Hand) {
		this.p2Hand = p2Hand;
	}

	public RoundResult getResult() {
		return result;
	}

	public void setResult(RoundResult result) {
		this.result = result;
	}

}
