import java.util.List;

public class Manager extends Thread{

    private final Catalog catalog;
    private boolean running = true;

    /**
     * Constructor care primeste catalogul in care se vor adauga toti cursantii
     * @param catalog: Catalog
     */
    public Manager(Catalog catalog) {
        this.catalog = catalog;
    }


    /**
     * Cat timp managerul inca ruleaza:
     *      - asteapta notificari pentru catalog ( notificari care vor fi transmise de catre secreatare)
     *      - cand a fost primita o notificare:
     *          * Obtine toti cursantii cu media sub 5
     *          * Afiseaza lista de cursanti
     */
    @Override
    public void run() {
        synchronized (catalog){
            while (running){
                try {
                    catalog.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                List<Cursant> cursanti = catalog.getCursanti();
                System.out.println("Lista cursantilor cu media sub 5:");
                cursanti.stream().filter((cursant) -> cursant.getMedie() < 5.0F).forEach(System.out::println);
                System.out.println();
            }
        }
    }

    public void stopManager(){
        running = false;
    }
}
