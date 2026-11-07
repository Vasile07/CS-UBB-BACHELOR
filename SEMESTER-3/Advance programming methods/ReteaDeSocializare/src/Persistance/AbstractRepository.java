package Persistance;

import Domain.Entity;
import jdk.jshell.spi.ExecutionControl;

public interface AbstractRepository<ID, E extends Entity<ID>> {

    /**
     * Add element
     * @param elem - Entity
     *             - not NULL
     * @throws:     ExistingEntityException if the entity with the id exist already
     *              InvalidArgumentException if the elem is null
     *              ValidationError is the elem is invalid
     *
     */
    void add(E elem);

    /**
     *
     * @param id - ID of the entity to be returned
     * @return Entity with the specified id
     * @throws:     InexistingEntityException if the entity with the id does not exist
     *              InvalidArgumentException if the id is null
     *
     */
    E findByID(ID id);

    /**
     *
     * @return All Entities
     */
    Iterable<E> getAll();

    /**
     *
     * @param id - ID of the entity to be deleted
     * @return the deleted entity
     * @throws:     InexistingEntityException if the entity with the id does not exist
     *              InvalidArgumentException if the id is null
     *
     */
    E deleteByID(ID id);

    /**
     *
     * @param elem - Entity
     * @return Entity with old dates
     * @throws:     InexistingEntityException if the entity with the id does not exist
     *              InvalidArgumentException if the elem is null
     *
     */
    void update(E elem);

    /**
     * Return the number of entities
     * @return Integer
     */
    Integer numberOfEntities();
}
