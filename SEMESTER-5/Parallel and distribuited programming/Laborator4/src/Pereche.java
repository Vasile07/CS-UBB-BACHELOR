import java.util.Objects;
public class Pereche implements Comparable<Pereche>{
    private Integer first;
    private Integer second;

    public Pereche(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pereche pereche = (Pereche) o;
        return Objects.equals(first, pereche.first) && Objects.equals(second, pereche.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public int compareTo(Pereche o) {
        return -1 * this.second.compareTo(o.getSecond());
    }
}