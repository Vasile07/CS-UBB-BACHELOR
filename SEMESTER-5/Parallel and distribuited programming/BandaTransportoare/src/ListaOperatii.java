import java.util.ArrayList;
import java.util.List;

public class ListaOperatii {
    private final List<Operatie> operatii = new ArrayList<>();

    public ListaOperatii() {
    }

    public List<Operatie> getOperatii() {
        synchronized (operatii){
            return new ArrayList<>(operatii);
        }
    }

    public void addOperatie(Operatie operatie) {
        synchronized (operatii){
            operatii.add(operatie);
        }
    }
}
