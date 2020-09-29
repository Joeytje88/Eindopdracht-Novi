package nl.tipsntricks.games.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException (String errorMessage) {
        super(errorMessage);
    }
}
