import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coada {
    private final List<Reteta> retete;

    private final Object lock = new Object();

    private volatile boolean isRunning = true;

    private Integer codReteta = 0;

    public Coada() {
        this.retete = new ArrayList<>();
    }

    public void add(Reteta reteta){
        synchronized (lock){
            reteta.setCodReteta(codReteta);
            codReteta++;
            retete.add(reteta);
            lock.notifyAll();
        }
    }

    public Reteta dequeue(){
        synchronized (lock){
            while(retete.isEmpty() && isRunning){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Reteta reteta = retete.isEmpty()? null : retete.remove(0);
            lock.notifyAll();
            return reteta;
        }
    }

    public void finish(){
        synchronized (lock){
            isRunning = false;
            lock.notifyAll();
        }
    }
}
