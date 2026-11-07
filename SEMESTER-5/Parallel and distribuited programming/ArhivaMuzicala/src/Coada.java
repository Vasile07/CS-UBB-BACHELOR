import java.util.ArrayList;
import java.util.List;

public class Coada {
    private final List<InregistrareMuzicala> inregistrari;
    private final Object lock = new Object();
    private Integer mesajeTotale = 0;
    private volatile boolean reading = true;

    public Coada() {
        inregistrari = new ArrayList<>();
    }

    public void add(InregistrareMuzicala inregistrareMuzicala){
        synchronized (lock){
            inregistrari.add(inregistrareMuzicala);
//            mesajeTotale ++;
            lock.notifyAll();
        }
    }

    public InregistrareMuzicala dequeue(){
        synchronized (lock){
            while (inregistrari.isEmpty() && reading) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }

            if(inregistrari.isEmpty())
                return null;
            mesajeTotale ++;
            return inregistrari.remove(0);
        }
    }

    public void finishReading(){
        synchronized (lock) {
            reading = false;
            lock.notifyAll();
        }
    }

    public Integer getMesajeTotale(){
        synchronized (lock){
            return mesajeTotale;
        }
    }
}
