import java.util.ArrayList;
import java.util.List;

public class CoadaCuCapacitate {
    private final List<Comanda> inregistrari;
    private final Object lock = new Object();
    private volatile boolean reading = true;
    private final Integer capacitate;

    public CoadaCuCapacitate(Integer capacitate) {
        this.capacitate = capacitate;
        inregistrari = new ArrayList<>();
    }

    public void add(Comanda comanda) {
        synchronized (lock) {
            while (inregistrari.size() >= capacitate){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            inregistrari.add(comanda);
            lock.notifyAll();
        }
    }

    public Comanda dequeue() {
        synchronized (lock) {
            while (inregistrari.isEmpty() && reading) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }

            Comanda comanda = inregistrari.isEmpty() ? null : inregistrari.remove(0);
            lock.notifyAll();
            return comanda;
        }
    }

    public void finishReading() {
        synchronized (lock) {
            reading = false;
            lock.notifyAll();
        }
    }

    public Integer getNumarComenzi() {
        synchronized (lock){
            return inregistrari.size();
        }
    }
}
