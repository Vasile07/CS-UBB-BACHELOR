import java.util.List;

public class Secretara extends Thread{
    private final Catalog catalog;
    private final List<Cursant> cursanti;

    /**
     * Constructor care primeste catalogul in care se vor adauga cursantii si lista de cursanti atribuita
     * @param catalog: Catalog
     * @param cursanti: List<Cursant>
     */
    public Secretara(Catalog catalog, List<Cursant> cursanti)
    {
        this.catalog = catalog;
        this.cursanti = cursanti;
    }

    /**
     * Pentru fiecare cursant atribuit:
     *      - asteapta 20 de secunde
     *      - adauga cursantul in catalog
     *      - daca media < 5.0, notifica managerul de asta
     */
    @Override
    public void run() {
        for(Cursant cursant : cursanti){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (catalog) {
                catalog.addCursant(cursant);
                if (cursant.getMedie() < 5.0F)
                    catalog.notify();
            }
        }
    }
}
