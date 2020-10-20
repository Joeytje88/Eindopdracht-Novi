package nl.tipsntricks.games.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CommentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler (CommentNotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public String commentNotFoundHandler (CommentNotFoundException ex)
    {return ex.getMessage();}
}
