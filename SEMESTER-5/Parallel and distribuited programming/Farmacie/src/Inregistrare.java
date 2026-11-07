import java.util.Objects;

public class Inregistrare {
    private Integer nrFactura;

    private Integer valoare;

    private Integer codReteta;

    public Inregistrare(Integer nrFactura, Integer valoare, Integer codReteta) {
        this.nrFactura = nrFactura;
        this.valoare = valoare;
        this.codReteta = codReteta;
    }

    public Integer getNrFactura() {
        return nrFactura;
    }

    public void setNrFactura(Integer nrFactura) {
        this.nrFactura = nrFactura;
    }

    public Integer getValoare() {
        return valoare;
    }

    public void setValoare(Integer valoare) {
        this.valoare = valoare;
    }

    public Integer getCodReteta() {
        return codReteta;
    }

    public void setCodReteta(Integer codReteta) {
        this.codReteta = codReteta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inregistrare that = (Inregistrare) o;
        return Objects.equals(nrFactura, that.nrFactura) && Objects.equals(valoare, that.valoare) && Objects.equals(codReteta, that.codReteta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrFactura, valoare, codReteta);
    }

    @Override
    public String toString() {
        return "Inregistrare{" +
                "nrFactura=" + nrFactura +
                ", valoare=" + valoare +
                ", codReteta=" + codReteta +
                '}';
    }
}
