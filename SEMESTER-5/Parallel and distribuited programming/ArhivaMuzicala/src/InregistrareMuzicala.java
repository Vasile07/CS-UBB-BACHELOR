import java.util.Objects;

public class InregistrareMuzicala {
    private String titlu;
    private String numeCompozitor;
    private Departament departament;

    public InregistrareMuzicala(String titlu, String numeCompozitor, Departament departament) {
        this.titlu = titlu;
        this.numeCompozitor = numeCompozitor;
        this.departament = departament;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getNumeCompozitor() {
        return numeCompozitor;
    }

    public void setNumeCompozitor(String numeCompozitor) {
        this.numeCompozitor = numeCompozitor;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InregistrareMuzicala that = (InregistrareMuzicala) o;
        return Objects.equals(titlu, that.titlu) && Objects.equals(numeCompozitor, that.numeCompozitor) && departament == that.departament;
    }

    @Override
    public int hashCode() {
        return Objects.hash(titlu, numeCompozitor, departament);
    }

    @Override
    public String toString() {
        return "InregistrareMuzicala{" +
                "titlu='" + titlu + '\'' +
                ", numeCompozitor='" + numeCompozitor + '\'' +
                ", departament=" + departament +
                '}';
    }
}
