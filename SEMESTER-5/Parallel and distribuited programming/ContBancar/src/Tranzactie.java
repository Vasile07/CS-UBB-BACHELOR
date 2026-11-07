import java.util.Objects;

public class Tranzactie {
    private Integer indexUser;
    private TipTranzactie tip;
    private TipValuta tipValuta;
    private Integer suma;
    private Integer sold;

    public Tranzactie(Integer indexUser, TipTranzactie tip, TipValuta tipValuta, Integer suma, Integer sold) {
        this.indexUser = indexUser;
        this.tip = tip;
        this.tipValuta = tipValuta;
        this.suma = suma;
        this.sold = sold;
    }

    public Integer getIndexUser() {
        return indexUser;
    }

    public void setIndexUser(Integer indexUser) {
        this.indexUser = indexUser;
    }

    public TipTranzactie getTip() {
        return tip;
    }

    public void setTip(TipTranzactie tip) {
        this.tip = tip;
    }

    public TipValuta getTipValuta() {
        return tipValuta;
    }

    public void setTipValuta(TipValuta tipValuta) {
        this.tipValuta = tipValuta;
    }

    public Integer getSuma() {
        return suma;
    }

    public void setSuma(Integer suma) {
        this.suma = suma;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tranzactie that = (Tranzactie) o;
        return Objects.equals(indexUser, that.indexUser) && tip == that.tip && tipValuta == that.tipValuta && Objects.equals(suma, that.suma) && Objects.equals(sold, that.sold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexUser, tip, tipValuta, suma, sold);
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "indexUser=" + indexUser +
                ", tip=" + tip +
                ", tipValuta=" + tipValuta +
                ", suma=" + suma +
                ", sold=" + sold +
                '}';
    }
}
