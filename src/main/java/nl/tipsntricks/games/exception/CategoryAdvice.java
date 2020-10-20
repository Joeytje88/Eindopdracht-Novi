package nl.tipsntricks.games.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CategoryAdvice {
    @ResponseBody
    @ExceptionHandler(CategoryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String categoryHandler (CategoryException ex){
        return ex.getMessage();
    }

}
