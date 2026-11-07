package Validator;

import Domain.User;
import Exceptions.ValidationException;

import java.util.Objects;

public class UserValidator implements AbstractValidator<User> {

    @Override
    public void validate(User entity) {
        String errors = "";
        if(entity.getName().isEmpty())
            errors += "Invalid name!\n";
        if(entity.getPassword().isEmpty())
            errors += "Invalid password!\n";
        if(!errors.isEmpty())
            throw new ValidationException(errors);
    }
}
