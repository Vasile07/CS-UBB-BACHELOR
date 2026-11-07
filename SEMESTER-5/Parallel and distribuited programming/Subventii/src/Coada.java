import java.util.ArrayList;
import java.util.List;

public class Coada {
    private final List<Cerere> cereri;

    private final Object lock = new Object();

    private volatile boolean isRunning = true;

    private final Integer capacitate;

    public Coada(Integer capacitate) {
        this.capacitate = capacitate;
        this.cereri = new ArrayList<>();
    }

    public void add(Cerere cerere) {
        synchronized (lock) {
            while (cereri.size() >= capacitate) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            cereri.add(cerere);
            lock.notifyAll();
        }
    }

    public Cerere dequeue() {
        synchronized (lock) {
            while (cereri.isEmpty() && isRunning) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Cerere cerere = cereri.isEmpty() ? null : cereri.remove(0);
            lock.notifyAll();
            return cerere;
        }
    }

    public void finish(){
        synchronized (lock){
            isRunning = false;
            lock.notifyAll();
        }
    }
}
