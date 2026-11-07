import java.util.List;

public class Iterator extends Thread {
    private final ListaOperatii listaOperatii;
    private volatile boolean running = true;

    public Iterator(ListaOperatii listaOperatii) {
        this.listaOperatii = listaOperatii;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                if (!running) {
                    break;
                }
            }

            List<Operatie> operatii = listaOperatii.getOperatii();

            operatii.forEach((operatie) -> System.out.println(operatie));
            System.out.println();

        }
    }

    public void stopIterator() {
        running = false;
        this.interrupt();
    }
}
