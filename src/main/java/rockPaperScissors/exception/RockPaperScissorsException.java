package rockPaperScissors.exception;

/**
 * This class defines a runtime exception produced on RockPaperScissors Game
 *
 * @author Antonio Sánchez
 *
 */

public class RockPaperScissorsException extends RuntimeException {


	private static final long serialVersionUID = 6120501626969829813L;



	/**
     * Creates a new empty ConfigurationRunTimeException
     */
    public RockPaperScissorsException() {

    }

    /**
     * Creates a new ConfigurationRunTimeException from the given Throwable object
     *
     * @param ex
     */
    public RockPaperScissorsException(final Throwable ex) {
        super(ex);
    }

    /**
     * Creates a new ConfigurationRunTimeException from the given Throwable object and with the passed message
     *
     * @param ex
     */
    public RockPaperScissorsException(final String msg, final Throwable ex) {
        super(msg, ex);
    }
    
    /**
     * Creates a new ConfigurationRunTimeExceptionwith the passed message
     *
     * @param ex
     */
    public RockPaperScissorsException(final String msg) {
        super(msg);
    }

}
