package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Entity<ID> implements Serializable {
    protected ID id;

    /**
     * Return the id of the entity
     * @return ID
     */
    public ID getId() {
        return id;
    }

    /**
     * Set the id of the entity
     * @param id ID
     */
    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
