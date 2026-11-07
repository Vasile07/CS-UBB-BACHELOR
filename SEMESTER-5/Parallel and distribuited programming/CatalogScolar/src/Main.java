import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static List<Cursant> genereazaCursanti(int nrCursanti){
        List<Cursant> cursanti = new ArrayList<>();
        for(int i = 0 ; i < nrCursanti ; i ++){
            Random random = new Random();
            Float medie = random.nextFloat(0.0F,10.0F);
            Cursant cursant = new Cursant(i, medie);
            cursanti.add(cursant);
        }
        return cursanti;
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 5; // Numarul thread-urilor "secretare"
        int nrCursanti = 10;
        int chunkLenght = nrCursanti / n; // Numarul de cursanti care trebuie adaugati per secreatara

        Catalog catalog = new Catalog();
        Thread[] secretare = new Thread[n];
        List<Cursant> cursanti = genereazaCursanti(nrCursanti);

        Manager manager = new Manager(catalog);
        manager.start();

        for(int threadIndex = 0 ; threadIndex < n ; threadIndex ++){
            int start = threadIndex * chunkLenght;
            int end = threadIndex == n - 1 ? nrCursanti : (threadIndex + 1) * chunkLenght;

            // Se creeaza cate un thread secreatara pentru fiecare portiune din lista de cursanti
            secretare[threadIndex] = new Secretara(catalog, cursanti.subList(start, end));

            secretare[threadIndex].start();
        }


        for(int threadIndex = 0 ; threadIndex < n ; threadIndex ++)
            secretare[threadIndex].join();

        manager.stopManager();

        // Notifica thread-ul manager pentru a afisa la final lista finala
        synchronized (catalog) {
            catalog.notify();
        }
    }
}