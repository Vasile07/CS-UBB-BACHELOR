import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    private static final List<String> users = Arrays.asList("User1","User2","User3","User4");
    private static final List<String> friends = Arrays.asList("Friend1","Friend2","Friend3","Friend4","Friend5","Friend6","Friend7","Friend8");

    public static void main(String[] args) throws InterruptedException {
        int nrWorkers = 2;
        int numarMesaje = 35;
        int N = 3;

        Thread[] workers = new Thread[nrWorkers];
        ListaPrieteni listaPrieteni = new ListaPrieteni(N);
        List<Pair<String, String>> mesaje = genereazaMesaje(numarMesaje);

        int chunkSize = numarMesaje / nrWorkers;

        for(int i = 0 ; i < nrWorkers ; i ++){
            int start = i * chunkSize;
            int end = i == nrWorkers-1 ? numarMesaje : (i+1) * chunkSize;
            workers[i] = new Worker(listaPrieteni, mesaje.subList(start, end));
        }

        Iterator iterator = new Iterator(listaPrieteni);
        iterator.start();

        for(int i  = 0 ; i < nrWorkers ; i ++)
            workers[i].start();

        for(int i  = 0 ; i < nrWorkers ; i ++)
            workers[i].join();

        iterator.stopIterator();
        synchronized (listaPrieteni) {
            listaPrieteni.notify();
        }
    }

    private static List<Pair<String, String>> genereazaMesaje(int numarMesaje) {
        List<Pair<String , String>> mesaje = new ArrayList<>();

        for(int i = 0 ; i < numarMesaje ; i ++){
            Random random = new Random();
            String numePersoana = users.get(random.nextInt(0, users.size()));
            String numePrieten = friends.get(random.nextInt(0, friends.size()));

            mesaje.add(new Pair<>(numePersoana, numePrieten));
        }
        return mesaje;
    }
}