import java.util.List;

public class Worker extends Thread{
    private final ListaPrieteni listaPrieteni;
    private final List<Pair<String, String>> mesaje;
    public Worker(ListaPrieteni listaPrieteni, List<Pair<String, String>> mesaje) {
        this.listaPrieteni = listaPrieteni;
        this.mesaje = mesaje;
    }

    @Override
    public void run() {
        for(Pair<String, String> mesaj : mesaje){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (listaPrieteni) {
                listaPrieteni.adaugaPrieten(mesaj.getFirst(), mesaj.getSecond());
            }
        }
    }
}
