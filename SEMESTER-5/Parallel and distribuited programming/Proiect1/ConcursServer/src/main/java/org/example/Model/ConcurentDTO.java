package org.example.Model;

import java.util.Objects;

public class ConcurentDTO {
    private String tara;
    private Integer idConcurent;
    private Integer punctaj;

    public ConcurentDTO(String tara, Integer idConcurent, Integer punctaj) {
        this.tara = tara;
        this.idConcurent = idConcurent;
        this.punctaj = punctaj;
    }

    public ConcurentDTO() {
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public Integer getIdConcurent() {
        return idConcurent;
    }

    public void setIdConcurent(Integer idConcurent) {
        this.idConcurent = idConcurent;
    }

    public Integer getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(Integer punctaj) {
        this.punctaj = punctaj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurentDTO that = (ConcurentDTO) o;
        return Objects.equals(tara, that.tara) && Objects.equals(idConcurent, that.idConcurent) && Objects.equals(punctaj, that.punctaj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tara, idConcurent, punctaj);
    }

    @Override
    public String toString() {
        return "ConcurentDTO{" +
                "tara='" + tara + '\'' +
                ", idConcurent=" + idConcurent +
                ", punctaj=" + punctaj +
                '}';
    }
}
