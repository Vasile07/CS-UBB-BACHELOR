import java.util.List;
import java.util.logging.Logger;

public class Worker extends Thread{
    private final SalaSpectacol salaSpectacol;
    private final List<Bilet> bilete;
    private final Logger logger = Logger.getLogger(Worker.class.getName());

    public Worker(SalaSpectacol salaSpectacol, List<Bilet> bilete) {
        this.salaSpectacol = salaSpectacol;
        this.bilete = bilete;
    }

    @Override
    public void run() {
        for(Bilet bilet : bilete){
            synchronized (salaSpectacol) {
            logger.info("Worker-ul " + this.threadId() + " inregistreaza biletul de pe randul " + bilet.getNumarRand() + " si locul " + bilet.getNumarLoc());
                salaSpectacol.inserare(bilet.getNumarRand(), bilet.getNumarLoc());
            }
        }
    }
}
