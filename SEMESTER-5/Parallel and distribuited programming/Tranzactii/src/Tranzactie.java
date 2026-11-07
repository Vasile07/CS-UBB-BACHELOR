import java.util.Objects;

public class Tranzactie {
    private String client;

    private String tip;

    private Integer suma;

    public Tranzactie(String client, String tip, Integer suma) {
        this.client = client;
        this.tip = tip;
        this.suma = suma;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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
        Tranzactie that = (Tranzactie) o;
        return Objects.equals(client, that.client) && Objects.equals(tip, that.tip) && Objects.equals(suma, that.suma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, tip, suma);
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "client='" + client + '\'' +
                ", tip='" + tip + '\'' +
                ", suma=" + suma +
                '}';
    }
}
