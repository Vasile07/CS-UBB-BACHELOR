import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int nrBilete = 100;
        int nrRanduri = 10;
        int nrLocuriPerRand = 7;
        List<Bilet> bilete = genereazaBilete(nrBilete, nrRanduri, nrLocuriPerRand);

        SalaSpectacol salaSpectacol = new SalaSpectacol(nrRanduri, nrLocuriPerRand);

        ThreadMonitorizare threadMonitorizare = new ThreadMonitorizare(salaSpectacol);
        threadMonitorizare.start();

        Iterator iterator = new Iterator(salaSpectacol, 25);
        iterator.start();

        int nrWorkeri = 2;
        Thread[] workeri = new Thread[nrWorkeri];

        int chunkSize = nrBilete / nrWorkeri;

        for(int i = 0 ; i < nrWorkeri ; i ++){
            int start = i*chunkSize;
            int end = i == nrWorkeri - 1 ? nrBilete : (i+1)*chunkSize;
            workeri[i] = new Worker(salaSpectacol, bilete.subList(start, end));

            workeri[i].start();
        }

        for(int i = 0 ; i < nrWorkeri ; i ++)
            workeri[i].join();

        iterator.stopIterator();
        threadMonitorizare.stopMonitorizare();

    }

    private static List<Bilet> genereazaBilete(int nrBilete, int nrRanduri, int nrLocuriPerRand) {
        List<Bilet> bilete = new ArrayList<>();
        Random random = new Random();
        for(int i = 0 ; i < nrBilete ; i ++){
            int rand = random.nextInt(0, nrRanduri);
            int loc = random.nextInt(0,nrLocuriPerRand);
            bilete.add(new Bilet(rand, loc));
        }
        return bilete;
    }
}