package nl.tipsntricks.games.exception;

public class GameNotFoundException extends RuntimeException{


    public GameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
