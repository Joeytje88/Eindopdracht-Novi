package nl.tipsntricks.games.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RoleNotFoundAdvice {


    @ResponseBody
    @ExceptionHandler (RoleNotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public String roleNotFoundHandler (RoleNotFoundException ex) {
        return ex.getMessage();
    }
}
