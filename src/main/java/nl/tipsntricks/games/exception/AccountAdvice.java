package nl.tipsntricks.games.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AccountAdvice {

    @ResponseBody
    @ExceptionHandler (AccountException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public String accountNotFoundHandler(AccountException ex)
    {return ex.getMessage();}
}
