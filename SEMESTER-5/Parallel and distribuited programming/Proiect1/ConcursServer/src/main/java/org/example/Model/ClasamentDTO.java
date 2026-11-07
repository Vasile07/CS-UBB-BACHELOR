package org.example.Model;

import java.util.Objects;

public class ClasamentDTO {
    private String id;
    private Integer punctajFinal;

    public ClasamentDTO(String id, Integer punctajFinal) {
        this.id = id;
        this.punctajFinal = punctajFinal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPunctajFinal() {
        return punctajFinal;
    }

    public void setPunctajFinal(Integer punctajFinal) {
        this.punctajFinal = punctajFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClasamentDTO that = (ClasamentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(punctajFinal, that.punctajFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, punctajFinal);
    }

    @Override
    public String toString() {
        return "ClasamentDTO{" +
                "id='" + id + '\'' +
                ", punctajFinal=" + punctajFinal +
                '}';
    }
}
