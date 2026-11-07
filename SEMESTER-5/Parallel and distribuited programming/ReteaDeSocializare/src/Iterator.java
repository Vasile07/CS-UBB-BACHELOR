import java.util.List;
import java.util.Map;

public class Iterator extends Thread {
    private final ListaPrieteni listaPrieteni;
    private volatile boolean running = true;

    public Iterator(ListaPrieteni listaPrieteni) {
        this.listaPrieteni = listaPrieteni;
    }

    @Override
    public void run() {
        while (running) {
            synchronized (listaPrieteni) {
                try {
                    listaPrieteni.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                Map<String, List<String>> prieteni = listaPrieteni.getListaPrieteni();
                for (String numePersoana : prieteni.keySet()) {
                    System.out.println(numePersoana + " : " + prieteni.get(numePersoana));
                }
                System.out.println();
            }
        }
    }

    public void stopIterator() {
        running = false;
    }
}
