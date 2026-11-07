import java.util.ArrayList;
import java.util.List;

public class Catalog{
    private final List<Cursant> cursanti;

    public Catalog() {
        cursanti = new ArrayList<>();
    }

    /**
     * Metoda thread-safe care adauga un cursant in lista de cursanti
     * @param cursant: Cursant
     */
    public void addCursant(Cursant cursant){
        synchronized (cursanti){
            cursanti.add(cursant);
        }
    }

    /**
     * Returneaza o copie a listei de cursanti din momentul apelarii
     * @return List<Cursant>
     */
    public List<Cursant> getCursanti(){
        synchronized (cursanti){
            return new ArrayList<>(cursanti);
        }
    }
}
