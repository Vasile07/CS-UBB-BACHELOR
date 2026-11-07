package Persistance;

import Domain.Entity;
import Exceptions.ExistingEntityException;
import Exceptions.InexistingEntityException;
import Validator.AbstractValidator;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID,E extends Entity<ID>> implements AbstractRepository<ID,E> {
    AbstractValidator<E> validator;
    Map<ID,E> listOfEntities;

    public InMemoryRepository(AbstractValidator<E> validator){
        this.validator = validator;
        listOfEntities = new HashMap<>();
    }

    /**
     * Add element
     * @param elem - Entity
     *             - not NULL
     * @throws:     ExistingEntityException if the entity with the id exist already
     *              InvalidArgumentException if the elem is null
     *              ValidationError is the elem is invalid
     *
     */
    @Override
    public void add(E elem){
        if(elem == null) {
            throw new IllegalArgumentException("Entitatea nu poate sa fie null!");
        }
        validator.validate(elem);
        if(listOfEntities.get(elem.getId()) != null){
            throw new ExistingEntityException("Exista inregistrata o entitate cu acelasi id!");
        }
        listOfEntities.put(elem.getId(),elem);
    }

    /**
     *
     * @param id - ID of the entity to be returned
     * @return Entity with the specified id
     * @throws:     InexistingEntityException if the entity with the id does not exist
     *              InvalidArgumentException if the id is null
     *
     */
    @Override
    public E findByID(ID id) {
        if(id == null){
            throw new IllegalArgumentException("Id-ul nu poate sa fie null!");
        }
        E entity = listOfEntities.get(id);
        if(entity == null){
            throw new InexistingEntityException("Id-ul nu a fost gasit");
        }
        return entity;
    }

    /**
     *
     * @return All Entities
     */
    @Override
    public Iterable<E> getAll() {
        return listOfEntities.values();
    }

    /**
     *
     * @param id - ID of the entity to be deleted
     * @return the deleted entity
     * @throws:     InexistingEntityException if the entity with the id does not exist
     *              InvalidArgumentException if the id is null
     *
     */
    @Override
    public E deleteByID(ID id) {
        if(id == null){
            throw new IllegalArgumentException("Id-ul nu poate sa foe null!");
        }
        E entity = listOfEntities.get(id);
        if(entity == null){
            throw new InexistingEntityException("Entitatea nu exista!");
        }
        listOfEntities.remove(id);
        return entity;
    }

    /**
     *
     * @param elem - Entity
     * @return Entity with old dates
     * @throws:     InexistingEntityException if the entity with the id does not exist
     *              InvalidArgumentException if the elem is null
     *
     */
    @Override
    public void update(E elem) {
        if(elem == null){
            throw new IllegalArgumentException("Entitatea nu poate sa fie null!");
        }
        E entity = listOfEntities.get(elem.getId());
        if(entity == null){
            throw new InexistingEntityException("Nu a fost inregistrata nici o entitate cu acest id!");
        }
        listOfEntities.put(elem.getId(),elem);
    }

    /**
     * Return the number of entities
     * @return Integer
     */
    @Override
    public Integer numberOfEntities() {
        return listOfEntities.size();
    }
}
