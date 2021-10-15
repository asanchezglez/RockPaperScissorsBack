package rockPaperScissors.model;

/**
 * @author antosanc
 * Player that always chooses Rock.
 */
public class RockPlayer implements IPlayer {

	@Override
	public Hand chooseHand() {
		return Hand.ROCK;
	}

}
