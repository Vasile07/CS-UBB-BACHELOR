import java.util.Objects;

public class Cerere {
    private Integer cod;

    private Integer valoare;

    public Cerere(Integer cod, Integer valoare) {
        this.cod = cod;
        this.valoare = valoare;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Integer getValoare() {
        return valoare;
    }

    public void setValoare(Integer valoare) {
        this.valoare = valoare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cerere cerere = (Cerere) o;
        return Objects.equals(cod, cerere.cod) && Objects.equals(valoare, cerere.valoare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod, valoare);
    }

    @Override
    public String toString() {
        return "Cerere{" +
                "cod=" + cod +
                ", valoare=" + valoare +
                '}';
    }
}
