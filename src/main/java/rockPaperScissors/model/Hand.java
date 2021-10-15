package rockPaperScissors.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author antosanc
 * Enum class with the possible hands to play
 *
 */

public enum Hand {
	@JsonProperty("ROCK")
	ROCK,
	@JsonProperty("PAPER")
	PAPER,
	@JsonProperty("SCISSORS")
	SCISSORS;
	
	/**
	
	@JsonCreator
	public Hand fromValue(String value) {
		return Hand.valueOf(value);
	}
	
	@JsonValue
	public String getName() {
        return super.name();
    }
    **/
}
