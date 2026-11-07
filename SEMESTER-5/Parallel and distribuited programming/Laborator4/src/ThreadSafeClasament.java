import java.util.*;

public class ThreadSafeClasament {
    private boolean inUsage;
    private final Map<Integer, Integer> punctaj;
    private final Set<Integer> nongrati;

    public ThreadSafeClasament() {
        punctaj = new HashMap<>();
        nongrati = new TreeSet<>();
        inUsage = false;
    }

    public synchronized void add(Pereche pereche) throws InterruptedException {
        while (inUsage) {
            wait();
        }
        inUsage = true;

        if (!nongrati.contains(pereche.getFirst())) {
            if(pereche.getSecond() == -1){
                punctaj.remove(pereche.getFirst());
                nongrati.add(pereche.getFirst());
            }else{
                if(punctaj.containsKey(pereche.getFirst()))
                    punctaj.put(pereche.getFirst(), pereche.getSecond() + punctaj.get(pereche.getFirst()));
                else
                    punctaj.put(pereche.getFirst(), pereche.getSecond());
            }
        }

        inUsage = false;
        notify();
    }

    public synchronized Set<Pereche> getClasament() {
        Set<Pereche> clasament = new TreeSet<>();
        for(Integer ID : punctaj.keySet())
            clasament.add(new Pereche(ID, punctaj.get(ID)));
        return clasament;
    }
}

