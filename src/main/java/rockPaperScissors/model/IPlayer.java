package rockPaperScissors.model;

/**
 * Interface to define players objects
 * @author antosanc
 *
 */
public interface IPlayer {
	
	/**
	 * Method for choosing the hand to play for a round
	 * @return chosen hand
	 */
	public Hand chooseHand();

}
