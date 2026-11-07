import java.util.List;
import java.util.Objects;

public class Reteta {
    private Integer codReteta;

    private Integer nrMedicamente;

    private List<Medicament> medicamente;

    public Reteta(Integer codReteta, Integer nrMedicamente, List<Medicament> medicamente) {
        this.codReteta = codReteta;
        this.nrMedicamente = nrMedicamente;
        this.medicamente = medicamente;
    }

    public Integer getCodReteta() {
        return codReteta;
    }

    public void setCodReteta(Integer codReteta) {
        this.codReteta = codReteta;
    }

    public Integer getNrMedicamente() {
        return nrMedicamente;
    }

    public void setNrMedicamente(Integer nrMedicamente) {
        this.nrMedicamente = nrMedicamente;
    }

    public List<Medicament> getMedicamente() {
        return medicamente;
    }

    public void setMedicamente(List<Medicament> medicamente) {
        this.medicamente = medicamente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reteta reteta = (Reteta) o;
        return Objects.equals(codReteta, reteta.codReteta) && Objects.equals(nrMedicamente, reteta.nrMedicamente) && Objects.equals(medicamente, reteta.medicamente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codReteta, nrMedicamente, medicamente);
    }

    @Override
    public String toString() {
        return "Reteta{" +
                "codReteta=" + codReteta +
                ", nrMedicamente=" + nrMedicamente +
                ", medicamente=" + medicamente +
                '}';
    }
}
