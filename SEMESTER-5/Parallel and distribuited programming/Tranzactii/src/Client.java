import java.util.Random;

public class Client extends Thread{
    private final Coada coada;

    private final Integer nrTranzactii;

    public Client(Coada coada, Integer nrTranzactii) {
        this.coada = coada;
        this.nrTranzactii = nrTranzactii;
    }

    @Override
    public void run() {
        for (int i = 0; i < nrTranzactii; i++){
            String client = String.valueOf(this.threadId());
            Random random = new Random();
            String tip;
            int val = random.nextInt(0, 2);
            if (val == 0){
                tip = "credit";
            }
            else{
                tip = "debit";
            }
            int suma = random.nextInt(0, 1000);
            Tranzactie tranzactie = new Tranzactie(client, tip, suma);
            coada.add(tranzactie);
        }
    }
}
