import java.util.*;

public class ListaPrieteni {
    private final Map<String, List<String>> listaPrieteni;
    private final Integer N;
    private Integer contor = 0;
    private final Object lock = new Object();

    public ListaPrieteni(int N) {
        listaPrieteni = new HashMap<>();
        this.N = N;
    }

    public void adaugaPrieten(String numePersoana, String numePrieten) {
        synchronized (lock) {
            if (!listaPrieteni.containsKey(numePersoana))
                listaPrieteni.put(numePersoana, new ArrayList<>());
            listaPrieteni.get(numePersoana).add(numePrieten);
            contor ++;
            if(contor%N == 0) {
                notify();
            }
        }
    }

    public Map<String, List<String>> getListaPrieteni() {
        synchronized (lock) {
            return new HashMap<>(listaPrieteni);
        }
    }
}
