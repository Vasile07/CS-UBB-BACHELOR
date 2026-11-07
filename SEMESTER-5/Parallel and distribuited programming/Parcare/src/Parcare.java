import java.util.Objects;

public class Parcare {
    private final Integer nrLocuriDeParcare;

    private Integer nrLocuriOcupate;

    private final Object lock = new Object();

    public Parcare(Integer nrLocuriDeParcare, Integer nrLocuriOcupate) {
        this.nrLocuriDeParcare = nrLocuriDeParcare;
        this.nrLocuriOcupate = nrLocuriOcupate;
    }

    public void iesire() {
        synchronized (lock) {
            if (nrLocuriOcupate != 0)
                nrLocuriOcupate--;
            lock.notifyAll();
        }
    }

    public void intrare() {
        synchronized (lock) {
            while (Objects.equals(nrLocuriOcupate, nrLocuriDeParcare)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            nrLocuriOcupate++;
            lock.notifyAll();
        }
    }

}
