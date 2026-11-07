import java.util.ArrayList;
import java.util.List;

public class CoadaCasierie{
    private final List<Inregistrare> inregistrari;

    private final Object lock = new Object();

    private volatile boolean isRunning = true;

    private Integer nrFactura = 0;

    public CoadaCasierie() {
        this.inregistrari = new ArrayList<>();
    }

    public void add(Inregistrare inregistrare){
        synchronized (lock){
            inregistrare.setCodReteta(nrFactura);
            nrFactura++;
            inregistrari.add(inregistrare);
            lock.notifyAll();
        }
    }

    public Inregistrare dequeue(){
        synchronized (lock){
            while(inregistrari.isEmpty() && isRunning){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Inregistrare inregistrare = inregistrari.isEmpty()? null : inregistrari.remove(0);
            lock.notifyAll();
            return inregistrare;
        }
    }

    public void finish(){
        synchronized (lock){
            isRunning = false;
            lock.notifyAll();
        }
    }
}

