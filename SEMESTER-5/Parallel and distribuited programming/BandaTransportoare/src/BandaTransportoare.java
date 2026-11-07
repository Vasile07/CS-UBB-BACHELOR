import java.util.Objects;

public class BandaTransportoare {

    private final Integer capacitate;
    private Integer cantitate;
    private final Object lock = new Object();

    public BandaTransportoare(Integer capacitate) {
        this.capacitate = capacitate;
        cantitate = 0;
    }

    public void consume(int quantity) {
        synchronized (lock){
            while(cantitate < quantity) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            cantitate -= quantity;
            lock.notifyAll();
        }
    }

    public void produce(int quantity) {
        synchronized (lock){
            while(cantitate + quantity >= capacitate){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            cantitate += quantity;

            lock.notifyAll();
        }
    }

    public Integer lenght() {
        synchronized (lock){
            return cantitate;
        }

    }
}
