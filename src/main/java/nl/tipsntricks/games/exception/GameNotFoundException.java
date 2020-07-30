package nl.tipsntricks.games.exception;

public class GameNotFoundException extends RuntimeException{


    /**
     * Creates the DogNotFoundException
     * @param id of the dog that was not found in the database
     */
    public GameNotFoundException(Long id) {
        super("Could not find game with id: " + id);
    }
}
