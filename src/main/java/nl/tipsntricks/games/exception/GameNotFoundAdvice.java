package nl.tipsntricks.games.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GameNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(GameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String gameNotFoundHandler(GameException ex) {
        return ex.getMessage();
    }
}
