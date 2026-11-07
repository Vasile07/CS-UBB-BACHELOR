package org.example;

import java.util.Objects;

public class TSEntity {
    private String atom;

    public TSEntity() {
    }

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TSEntity tsEntity = (TSEntity) o;
        return Objects.equals(atom, tsEntity.atom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atom);
    }

    @Override
    public String toString() {
        return "TSEntity{" +
                "atom='" + atom + '\'' +
                '}';
    }
}
