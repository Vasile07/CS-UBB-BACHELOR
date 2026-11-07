import javax.management.OperationsException;
import java.util.LinkedList;
import java.util.Queue;

public class ThreadSafeQueue {
    private Queue<Pereche> perechi;
    private int readers;
    private boolean finished;

    public ThreadSafeQueue(int readers) {
        perechi = new LinkedList<>();
        this.readers = readers;
        this.finished = readers != 0;
    }

    public synchronized void produce(Pereche pereche) throws InterruptedException {
        perechi.add(pereche);
        notify();
    }

    public synchronized Pereche consume() throws InterruptedException {
        while (perechi.isEmpty() && !finished) {
            wait();
        }
        if (!perechi.isEmpty()) {
            Pereche pereche = perechi.poll();
            notify();
            return pereche;
        }
        return null;
    }

    public synchronized boolean hasValues() {
        return !perechi.isEmpty() || readers > 0;
    }

    public synchronized void finish() {
        readers--;
        if (readers == 0) {
            finished = true;
            notifyAll();
        }
    }
}
