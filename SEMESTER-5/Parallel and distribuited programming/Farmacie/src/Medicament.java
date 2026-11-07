import java.util.Objects;

public class Medicament {
    private Integer cod;

    private Integer pret;

    public Medicament(Integer cod, Integer pret) {
        this.cod = cod;
        this.pret = pret;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Integer getPret() {
        return pret;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament that = (Medicament) o;
        return Objects.equals(cod, that.cod) && Objects.equals(pret, that.pret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod, pret);
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "cod=" + cod +
                ", pret=" + pret +
                '}';
    }
}
