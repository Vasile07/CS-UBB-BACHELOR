import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 100;
        int M = 4;
        int S = 1;
        int capacitate = 20;
        AtomicInteger fonduriCurente = new AtomicInteger(0);
        Integer sumaAlocata = 0;

        Thread[] cetateni = new Thread[N];
        Thread[] angajati = new Thread[M];
        Thread[] supervizori = new Thread[S];

        Object lock = new Object();
        Object lockFonduri = new Object();

        Coada coada = new Coada(capacitate);

        for (int i = 0; i < N; i++){
            cetateni[i] = new Cetatean(coada);
        }

        for (int i = 0; i < M; i++){
            angajati[i] = new Angajat(coada, fonduriCurente, sumaAlocata, lock, lockFonduri);
        }

        for (int i = 0; i < S; i++){
            supervizori[i] = new Supervizor(fonduriCurente, lockFonduri);
        }

        for (int i = 0; i < N; i++){
            cetateni[i].start();
        }


        for (int i = 0; i < M; i++){
            angajati[i].start();
        }

        for (int i = 0; i < S; i++){
            supervizori[i].start();
        }

        for (int i = 0; i < N; i++){
            cetateni[i].join();
        }

        coada.finish();

        for (int i = 0; i < M; i++){
            angajati[i].join();
        }

        for (int i = 0; i < S; i++){
            supervizori[i].join();
        }

    }
}