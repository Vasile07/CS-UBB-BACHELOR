import java.util.Random;

public class Cetatean extends Thread{
    private final Coada coada;
    public Cetatean(Coada coada) {
        this.coada = coada;
    }

    @Override
    public void run() {
        Random random = new Random();
        Integer cod = random.nextInt(0, 1000);
        Integer valoare = random.nextInt(1, 31);
        Cerere cerere = new Cerere(cod, valoare);
        coada.add(cerere);
    }
}
