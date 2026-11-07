import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SistemSupraveghere extends Thread {

    private static final Logger logger = Logger.getLogger(SistemSupraveghere.class.getName());
    private final Coada coada;
    private final MapTitlu mapTitlu;
    private final MapCompozitor mapCompozitor;
    private final Integer TA;

    private volatile boolean running = true;

    public SistemSupraveghere(Coada coada, MapTitlu mapTitlu, MapCompozitor mapCompozitor, Integer ta) {
        this.coada = coada;
        this.mapTitlu = mapTitlu;
        this.mapCompozitor = mapCompozitor;
        TA = ta;

        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("application.log", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(TA);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (coada) {
                synchronized (mapTitlu) {
                    synchronized (mapCompozitor) {
                        Integer mesajeTotale = coada.getMesajeTotale();
                        Integer mesajeTitlu = mapTitlu.getMap().values()
                                .stream()
                                .mapToInt(List::size)
                                .sum();
                        Integer mesajeCompozitor = mapCompozitor.getMap().values()
                                .stream()
                                .mapToInt(List::size)
                                .sum();

                        logger.info("Mesaje totale trimise %d; Mesaje adaugate in titlu: %d; Mesaje adaugate in compozitor: %d".formatted(mesajeTotale, mesajeTitlu, mesajeCompozitor));
                    }
                }
            }
        }
    }

    public void stopSupraveghere() {
        running = false;
    }
}
