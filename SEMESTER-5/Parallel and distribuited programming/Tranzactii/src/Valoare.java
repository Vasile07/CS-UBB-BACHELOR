import java.util.Objects;

public class Valoare {
    private String tip;

    private Integer suma;

    public Valoare(String tip, Integer suma) {
        this.tip = tip;
        this.suma = suma;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getSuma() {
        return suma;
    }

    public void setSuma(Integer suma) {
        this.suma = suma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valoare valoare = (Valoare) o;
        return Objects.equals(tip, valoare.tip) && Objects.equals(suma, valoare.suma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tip, suma);
    }

    @Override
    public String toString() {
        return "Valoare{" +
                "tip='" + tip + '\'' +
                ", suma=" + suma +
                '}';
    }
}
