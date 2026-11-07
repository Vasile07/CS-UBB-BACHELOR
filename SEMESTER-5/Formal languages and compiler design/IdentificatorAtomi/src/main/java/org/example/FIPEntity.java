package org.example;

import java.util.Objects;

public
class FIPEntity{
    private int cod;
    private int pozInTS;

    public FIPEntity() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getPozInTS() {
        return pozInTS;
    }

    public void setPozInTS(int pozInTS) {
        this.pozInTS = pozInTS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FIPEntity fipEntity = (FIPEntity) o;
        return cod == fipEntity.cod && pozInTS == fipEntity.pozInTS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod, pozInTS);
    }

    @Override
    public String toString() {
        return "FIPEntity{" +
                "cod=" + cod +
                ", pozInTS=" + pozInTS +
                '}';
    }
}