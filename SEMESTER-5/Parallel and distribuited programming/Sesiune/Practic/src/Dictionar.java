import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionar {
    private Map<Integer, List<Integer>> comenzi;
    private final Object lock = new Object();

    public Dictionar(){
        comenzi = new HashMap<>();
    }

    public void add(Comanda comanda) {
        synchronized (lock){
            if(!comenzi.containsKey(comanda.getIdOperator()))
                comenzi.put(comanda.getIdOperator(), new ArrayList<>());
            comenzi.get(comanda.getIdOperator()).add(comanda.getIdComanda());
        }
    }

    public Map<Integer, List<Integer>> getComenzi(){
        synchronized (lock){
            return new HashMap<>(comenzi);
        }
    }
}
