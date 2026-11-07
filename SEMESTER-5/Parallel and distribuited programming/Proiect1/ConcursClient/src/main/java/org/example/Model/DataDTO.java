package org.example.Model;

import java.util.Objects;

public class DataDTO {
    private String tara;
    private Integer idConcurent;
    private Integer punctaj;

    public DataDTO(String tara, Integer idConcurent, Integer punctaj) {
        this.tara = tara;
        this.idConcurent = idConcurent;
        this.punctaj = punctaj;
    }

    public DataDTO() {
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
        DataDTO dataDTO = (DataDTO) o;
        return Objects.equals(tara, dataDTO.tara) && Objects.equals(idConcurent, dataDTO.idConcurent) && Objects.equals(punctaj, dataDTO.punctaj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tara, idConcurent, punctaj);
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "tara='" + tara + '\'' +
                ", idConcurent=" + idConcurent +
                ", punctaj=" + punctaj +
                '}';
    }
}
