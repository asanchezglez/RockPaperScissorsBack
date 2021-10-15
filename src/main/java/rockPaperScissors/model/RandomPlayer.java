/**
 * 
 */
package rockPaperScissors.model;

import java.util.Random;

/**
 * @author antosanc
 * Player that will randomly choose its hand to play
 */
public class RandomPlayer implements IPlayer {

	@Override
	public Hand chooseHand() {
		Random random = new Random();
		int handPos = random.nextInt(Hand.values().length);
		return Hand.values()[handPos];
	}

}
