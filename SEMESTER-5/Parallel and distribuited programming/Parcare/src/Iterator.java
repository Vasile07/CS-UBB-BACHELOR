import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Iterator extends Thread {
    private final ListaOperatii listaOperatii;

    private final Integer nrLocuriOcupate;

    private final Integer nrLocuriTotale;

    private volatile boolean isRunning = true;

    public Iterator(ListaOperatii listaOperatii, Integer nrLocuriOcupate, Integer nrLocuriTotale) {
        this.listaOperatii = listaOperatii;
        this.nrLocuriOcupate = nrLocuriOcupate;
        this.nrLocuriTotale = nrLocuriTotale;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                if (!isRunning)
                    break;
            }

            List<String> operatii = listaOperatii.getOperatii();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("operatii.txt"))) {
                AtomicInteger nrLocuriDisponibile = new AtomicInteger(nrLocuriTotale - nrLocuriOcupate);
                operatii.forEach((operatie) -> {
                    if (operatie == "iesire")
                        nrLocuriDisponibile.getAndIncrement();
                    else
                        nrLocuriDisponibile.getAndDecrement();
                    try {
                        writer.write(operatie + "," + nrLocuriDisponibile);
                        writer.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopIterator() {
        isRunning = false;
    }
}
