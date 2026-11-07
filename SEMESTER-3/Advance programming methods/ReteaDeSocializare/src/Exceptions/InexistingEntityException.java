package Exceptions;

public class InexistingEntityException extends RuntimeException{
    public InexistingEntityException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
