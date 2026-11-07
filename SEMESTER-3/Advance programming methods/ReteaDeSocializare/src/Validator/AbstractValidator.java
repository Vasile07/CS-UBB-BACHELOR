package Validator;

import Domain.Entity;
import Domain.Message;

public interface AbstractValidator<T> {
    void validate(T entity);
    //void valideaza(Entity<ID> E);
}
