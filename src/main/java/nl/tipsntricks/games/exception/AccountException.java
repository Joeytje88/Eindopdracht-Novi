package nl.tipsntricks.games.exception;

public class AccountException extends RuntimeException{
    public AccountException(String errorMessage) {
        super(errorMessage);
    }
}
