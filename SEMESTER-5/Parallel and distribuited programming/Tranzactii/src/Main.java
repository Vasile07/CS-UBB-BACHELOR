public class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 4;

        Coada coada = new Coada();
        Thread[] clienti = new Thread[N];
        Thread banca = new Banca(coada);

        for (int i = 0; i < N; i++){
            clienti[i] = new Client(coada, 100);
        }

        for (int i = 0; i < N; i++){
            clienti[i].start();
        }

        banca.start();

        for (int i = 0; i < N; i++){
            clienti[i].join();
        }
        coada.finish();
        banca.join();
    }
}