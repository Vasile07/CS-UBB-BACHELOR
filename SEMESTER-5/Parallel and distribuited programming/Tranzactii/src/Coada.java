import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coada {
    private final List<Tranzactie> tranzactii;

    private final Object lock = new Object();

    private volatile boolean isRunning = true;

    public Coada(){
        this.tranzactii = new ArrayList<>();
    }

    public void add(Tranzactie tranzactie){
        synchronized (lock){
            tranzactii.add(tranzactie);
            lock.notifyAll();
        }
    }

    public Tranzactie dequeue(){
        synchronized (lock){
            while (tranzactii.isEmpty() && isRunning){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Tranzactie tranzactie = tranzactii.isEmpty()? null : tranzactii.remove(0);
            lock.notifyAll();
            return tranzactie;
        }
    }

    public void finish(){
        synchronized (lock){
            isRunning = false;
            lock.notifyAll();
        }
    }
}
