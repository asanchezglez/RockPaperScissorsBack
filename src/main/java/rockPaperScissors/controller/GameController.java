package rockPaperScissors.controller;

import java.util.List;

//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import rockPaperScissors.exception.RockPaperScissorsException;
import rockPaperScissors.manager.GameManager;
import rockPaperScissors.model.Game;
import rockPaperScissors.model.GameStatistics;
import rockPaperScissors.model.Hand;
import rockPaperScissors.model.IPlayer;
import rockPaperScissors.model.RandomPlayer;
import rockPaperScissors.model.RockPlayer;
import rockPaperScissors.model.Round;



/**
 * Rest Service class for game managing
 * @author antosanc
 *
 */
@RequestMapping("/game")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class GameController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
	
	/**
	 * Creates a new game
	 * @param playerType Opponent player type
	 * @return game Id
	 */
	@PostMapping("/createGame")
	public Game createGame(@RequestParam("playerType") String playerType) {
		IPlayer player = null;
		LOGGER.info("createGame: " + playerType);
		if (playerType.equals("ROCK")) {
			player = new RockPlayer();
		} else {//RandomPlayer by default
			player = new RandomPlayer();
		}
		int gameId = GameManager.getInstance().createGame(player);
		Game result = new Game(gameId);
		String message = "{\"gameId\": \"%s\"}";
		LOGGER.info("created game with id: " + gameId);
		//return Response.ok(String.format(message,gameId)).build();
		return result;
	}
	
	/**
	 * Plays one round of the game
	 * @param gameId Id of the game
	 * @param hand Hand the human player has played
	 * @return result of the round
	 */
	//@POST
	//@Path("/{gameId}")
	//@Produces(MediaType.APPLICATION_JSON)
	@PostMapping("/{gameId}")
	public Round playRound(@PathVariable("gameId")  int gameId, @RequestParam("hand") Hand handPlayed) {
		LOGGER.info("playRound for GameId " + gameId + " and hand " + handPlayed);

		//ResponseBuilder response = null;
		//Round result;
		//try {
		Round result = GameManager.getInstance().playRound(gameId, handPlayed); //Catch needed?
			//response = Response.ok(result);
			LOGGER.info("playRound for GameId " + gameId + " and hand " + handPlayed +" Result " + result.getResult());
//		}catch (Exception exc) {
//			LOGGER.error("Error on playRound for gameId " + gameId +". Message: "+ exc.getMessage());
//			//response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc.getMessage());
//		}
		//return response.build();
			return result;
	}
	
	/**
	 * Returns the current rounds of a given game
	 * @param gameId Id of the game
	 * @return List of rounds
	 */
	//@GET
	@GetMapping("/{gameId}")
	public List<Round> getGameRounds(@PathVariable("gameId") int gameId) {
		LOGGER.info("getGameRounds for gameId " + gameId);
		//ResponseBuilder response = null;
		//try {
		 List<Round> gameRounds = GameManager.getInstance().getGameRounds(gameId);
		 //response = Response.ok(gameRounds);
//		}catch (Exception exc) {
//			LOGGER.error("Error on getGameRounds for gameId " + gameId +". Message: "+ exc.getMessage());
//			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc.getMessage());
//			
//		}
		//return response.build();
		 return gameRounds;
	}
	
	/**
	 * Returns general statistics
	 * @return statistics
	 */
	@GetMapping("/")
	public GameStatistics getStatistics() {
		LOGGER.info("getStatistics");
		//return Response.ok(GameManager.getInstance().getStatistics()).build();
		return GameManager.getInstance().getStatistics();
	}
	
	/**
	 * Restarts the given game
	 * @param gameId id of the game to restart
	 */
	@PostMapping("/{gameId}/restart")
	public void restartGame(@PathVariable("gameId") int gameId) {
		LOGGER.info("restartGame for gameId " + gameId);
		//try {
			GameManager.getInstance().restartGame(gameId);
//		}catch (Exception exc) {
//			LOGGER.error("Error on restartGame for gameId " + gameId +". Message: "+ exc.getMessage());
//			Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc.getMessage());
//			
//		}
		//return Response.accepted().build();
	}
	
	@ExceptionHandler({RockPaperScissorsException.class})
	public ResponseEntity handleException(RockPaperScissorsException ex) {
		ResponseEntity response = new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
	
	
	

}
