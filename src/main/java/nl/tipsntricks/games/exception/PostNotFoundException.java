package nl.tipsntricks.games.exception;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException (String errorMessage) {
        super (errorMessage);
    }
}
