public class Iterator extends Thread {
    private final ListaTranzactii tranzactii;
    private volatile boolean running = true;

    public Iterator(ListaTranzactii tranzactii) {
        this.tranzactii = tranzactii;
    }

    @Override
    public void run() {
        while (running) {
            synchronized (tranzactii) {
                try {
                    tranzactii.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            tranzactii.getTranzactii().forEach((tranzactie -> System.out.println(tranzactie)));
            System.out.println();
        }
    }

    public void stopIterator() {
        running = false;
    }
}
