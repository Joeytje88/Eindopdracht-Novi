package nl.tipsntricks.games.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
