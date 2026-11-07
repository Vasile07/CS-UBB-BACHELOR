import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void genereazaMedicamente() {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("medicamente.txt"))) {
            for (int i = 0; i < 30; i++) {
                Integer cod = i;
                int pret = random.nextInt(1, 100);
                writer.write(cod + "," + pret);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        genereazaMedicamente();
        int N = 100;
        int M = 4;
        int f = 1;

        Thread[] pacienti = new Thread[N];
        Thread[] farmacisti = new Thread[M];
        Thread[] casieri = new Thread[f];

        Coada coada = new Coada();
        CoadaCasierie coadaCasierie = new CoadaCasierie();

        for (int i = 0; i < N; i++) {
            pacienti[i] = new Pacient(coada);
        }

        for (int i = 0; i < M; i++) {
            farmacisti[i] = new Farmacist(coada, coadaCasierie);
        }

        for (int i = 0; i < f; i++) {
            casieri[i] = new Casier(coadaCasierie);
        }

        for (int i = 0; i < N; i++) {
            pacienti[i].start();
        }

        for (int i = 0; i < M; i++) {
            farmacisti[i].start();
        }

        for (int i = 0; i < f; i++) {
            casieri[i].start();
        }
        for (int i = 0; i < N; i++) {
            pacienti[i].join();
        }

        coada.finish();

        for (int i = 0; i < M; i++) {
            farmacisti[i].join();
        }

        coadaCasierie.finish();

        for (int i = 0; i < f; i++) {
            casieri[i].join();
        }
    }
}