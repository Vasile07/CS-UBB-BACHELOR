import java.util.ArrayList;
import java.util.List;

public class ListaOperatii {
    private final List<String> operatii = new ArrayList<>();

    public ListaOperatii(){

    }

    public List<String> getOperatii(){
        synchronized (operatii){
            return new ArrayList<>(operatii);
        }
    }

    public void addOperatie(String operatie){
        synchronized (operatii){
            operatii.add(operatie);
        }
    }
}
