package nl.tipsntricks.games.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TagAdvice {
    @ResponseBody
    @ExceptionHandler(TagException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public String tagNotFoundHandler (TagException ex)
    {return ex.getMessage();}
}
