package org.example;

import java.util.List;

public class EroareLexicala extends Exception{
    private final List<String> errors;

    public EroareLexicala(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        StringBuilder errorMessage = new StringBuilder();
        for(String error : errors)
            errorMessage.append(error).append("\n");
        return errorMessage.toString();
    }
}
