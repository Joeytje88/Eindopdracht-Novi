package nl.tipsntricks.games.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException (String errorMessage) {
        super (errorMessage);
    }

}
