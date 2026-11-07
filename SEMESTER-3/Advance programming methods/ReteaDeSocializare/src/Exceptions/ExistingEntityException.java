package Exceptions;

public class ExistingEntityException extends RuntimeException{
    public ExistingEntityException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
