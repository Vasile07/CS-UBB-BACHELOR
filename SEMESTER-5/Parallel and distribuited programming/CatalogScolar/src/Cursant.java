import java.util.Objects;

public class Cursant{
    private Integer ID;
    private Float medie;
    public Cursant(Integer ID, Float medie) {
        this.ID = ID;
        this.medie = medie;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Float getMedie() {
        return medie;
    }

    public void setMedie(Float medie) {
        this.medie = medie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cursant cursant = (Cursant) o;
        return Objects.equals(ID, cursant.ID) && Objects.equals(medie, cursant.medie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, medie);
    }

    @Override
    public String toString() {
        return "Cursant{" +
                "ID=" + ID +
                ", medie=" + medie +
                '}';
    }
}
