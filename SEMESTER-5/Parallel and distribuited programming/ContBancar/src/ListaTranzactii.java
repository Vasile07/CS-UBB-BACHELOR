import java.util.ArrayList;
import java.util.List;

public class ListaTranzactii {
    private final List<Tranzactie> tranzactii;

    public ListaTranzactii() {
        tranzactii = new ArrayList<>();
    }

    public void adaugaTranzactie(Tranzactie tranzactie){
        synchronized (tranzactii){
            tranzactii.add(tranzactie);
            if(tranzactii.size() % 5 == 0) {
                notify();
            }
        }
    }

    public List<Tranzactie> getTranzactii(){
        synchronized (tranzactii){
            return new ArrayList<>(tranzactii);
        }
    }
}
