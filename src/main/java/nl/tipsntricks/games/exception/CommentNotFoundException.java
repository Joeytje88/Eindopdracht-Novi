package nl.tipsntricks.games.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException (long id) {
        super("Reactie met id: " + id + " bestaat niet.");
    }
    public CommentNotFoundException (String errorMessage) {
        super(errorMessage);
    }
}
