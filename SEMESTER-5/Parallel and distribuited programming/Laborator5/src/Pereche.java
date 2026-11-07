import java.util.Objects;
public class Pereche implements Comparable<Pereche>{
    private Integer first;
    private Integer second;
    private Integer tara;

    public Pereche(Integer first, Integer second, Integer tara) {
        this.first = first;
        this.second = second;
        this.tara = tara;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }
    public Integer getTara(){return tara;}

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
        return Objects.equals(first, pereche.first) && Objects.equals(second, pereche.second) && Objects.equals(tara, pereche.tara);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, tara);
    }

    @Override
    public int compareTo(Pereche o) {
        if (Objects.equals(this.second, o.second))
            return this.first - o.first;
        return -1 * (this.second - o.second);
    }
}