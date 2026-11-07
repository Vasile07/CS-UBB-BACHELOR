import java.util.Objects;

public class Comanda {
    private Integer idOperator;
    private Integer idComanda;
    private Integer prioritate;

    public Comanda(Integer idOperator, Integer idComanda, Integer prioritate) {
        this.idOperator = idOperator;
        this.idComanda = idComanda;
        this.prioritate = prioritate;
    }

    public Integer getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(Integer idOperator) {
        this.idOperator = idOperator;
    }

    public Integer getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
    }

    public Integer getPrioritate() {
        return prioritate;
    }

    public void setPrioritate(Integer prioritate) {
        this.prioritate = prioritate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return Objects.equals(idOperator, comanda.idOperator) && Objects.equals(idComanda, comanda.idComanda) && Objects.equals(prioritate, comanda.prioritate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOperator, idComanda, prioritate);
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "idOperator=" + idOperator +
                ", idComanda=" + idComanda +
                ", prioritate=" + prioritate +
                '}';
    }
}
