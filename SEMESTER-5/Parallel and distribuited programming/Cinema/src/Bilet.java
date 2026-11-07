import java.util.Objects;

public class Bilet {
    private Integer numarRand;
    private Integer numarLoc;

    public Bilet(Integer numarRand, Integer numarLoc) {
        this.numarRand = numarRand;
        this.numarLoc = numarLoc;
    }

    public Integer getNumarRand() {
        return numarRand;
    }

    public void setNumarRand(Integer numarRand) {
        this.numarRand = numarRand;
    }

    public Integer getNumarLoc() {
        return numarLoc;
    }

    public void setNumarLoc(Integer numarLoc) {
        this.numarLoc = numarLoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bilet bilet = (Bilet) o;
        return Objects.equals(numarRand, bilet.numarRand) && Objects.equals(numarLoc, bilet.numarLoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numarRand, numarLoc);
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "numarRand=" + numarRand +
                ", numarLoc=" + numarLoc +
                '}';
    }
}
