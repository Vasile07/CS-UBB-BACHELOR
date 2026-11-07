import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        int F = 5;
        int P = 2;
        int C = 3;
        genereazaFisiere(F, 5);

        List<String> numeFisiere = getNumeFisiere(F);
        int chunkSize = F / P;

        Coada coada = new Coada();
        MapTitlu mapTitlu = new MapTitlu();
        MapCompozitor mapCompozitor = new MapCompozitor();

        Thread[] producatori = new Thread[P];
        Thread[] consumatori = new Thread[C];

        for (int i = 0; i < P; i++) {
            int start = i * chunkSize;
            int end = i == P - 1 ? numeFisiere.size() : (i + 1) * chunkSize;

            producatori[i] = new Producator(coada, numeFisiere.subList(start, end));
        }

        for (int i = 0; i < C; i++) {

            consumatori[i] = new Operator(coada, mapTitlu, mapCompozitor);
        }


        SistemSupraveghere sistemSupraveghere = new SistemSupraveghere(coada, mapTitlu, mapCompozitor, 10);
        sistemSupraveghere.start();

        for (int i = 0; i < P; i++) {
            producatori[i].start();
        }

        for (int i = 0; i < P; i++) {
            consumatori[i].start();
        }

        for (int i = 0; i < P; i++) {
            producatori[i].join();
        }


        synchronized (coada) {
            coada.finishReading();
        }

        for (int i = 0; i < C; i++) {
            consumatori[i].join();
        }

        sistemSupraveghere.stopSupraveghere();
        sistemSupraveghere.join();

        synchronized (mapTitlu) {
            Map<Character, List<InregistrareMuzicala>> mapT = mapTitlu.getMap();
            mapT.keySet().forEach(key -> System.out.println(key + " : " + mapT.get(key)));
            System.out.println(mapT.size());
            System.out.println();
        }

        synchronized (mapCompozitor) {
            Map<Character, List<InregistrareMuzicala>> mapC = mapCompozitor.getMap();
            mapC.keySet().forEach(key -> System.out.println(key + " : " + mapC.get(key)));
            System.out.println();
        }
    }

    private static List<String> getNumeFisiere(int f) {
        List<String> numeFisiere = new ArrayList<>();

        for (int i = 1; i <= f; i++)
            numeFisiere.add("Piese/Fisier%d.txt".formatted(i));

        return numeFisiere;
    }


    private static String genereazaStringRandom(int lungime) {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < lungime; i++) {
            int randomCodePoint = random.nextInt(leftLimit, rightLimit + 1);
            s.append((char) randomCodePoint);
        }

        return s.toString();
    }

    private static InregistrareMuzicala genereazaInregistrareMuzicalaRandom() {

        String titlu = genereazaStringRandom(6);
        String numeCompozitor = genereazaStringRandom(6);
        Departament departament;
        Random random = new Random();
        switch (random.nextInt(0, 3 + 1)) {
            case 0 -> {
                departament = Departament.pop;
            }
            case 1 -> {

                departament = Departament.rock;
            }
            case 2 -> {
                departament = Departament.clasic;
            }
            default -> {

                departament = Departament.populara;
            }
        }
        return new InregistrareMuzicala(titlu, numeCompozitor, departament);
    }

    private static void genereazaFisiere(int f, int n) {
        for (int i = 1; i <= f; i++) {
            String numeFisier = "Piese/Fisier%d.txt".formatted(i);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
                for (int k = 0; k < n; k++) {
                    InregistrareMuzicala inregistrareMuzicala = genereazaInregistrareMuzicalaRandom();
                    writer.write(inregistrareMuzicala.getTitlu() + "," + inregistrareMuzicala.getNumeCompozitor() + "," + inregistrareMuzicala.getDepartament());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}