package org.example.Model;

import java.util.List;
import java.util.Objects;

public class ClasamentFinalDTO {
    private List<ClasamentDTO> clasamentTari;
    private List<ClasamentDTO> clasamentConcurenti;

    public ClasamentFinalDTO(List<ClasamentDTO> clasamentTari, List<ClasamentDTO> clasamentConcurenti) {
        this.clasamentTari = clasamentTari;
        this.clasamentConcurenti = clasamentConcurenti;
    }

    public ClasamentFinalDTO() {
    }

    public List<ClasamentDTO> getClasamentTari() {
        return clasamentTari;
    }

    public void setClasamentTari(List<ClasamentDTO> clasamentTari) {
        this.clasamentTari = clasamentTari;
    }

    public List<ClasamentDTO> getClasamentConcurenti() {
        return clasamentConcurenti;
    }

    public void setClasamentConcurenti(List<ClasamentDTO> clasamentConcurenti) {
        this.clasamentConcurenti = clasamentConcurenti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClasamentFinalDTO that = (ClasamentFinalDTO) o;
        return Objects.equals(clasamentTari, that.clasamentTari) && Objects.equals(clasamentConcurenti, that.clasamentConcurenti);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clasamentTari, clasamentConcurenti);
    }

    @Override
    public String toString() {
        return "ClasamentFinalDTO{" +
                "clasamentTari=" + clasamentTari +
                ", clasamentConcurenti=" + clasamentConcurenti +
                '}';
    }
}
