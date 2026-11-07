import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

//        generazaFisiere(5);
        long start = System.currentTimeMillis();
//        secvential(5);
        paralel(5,4, 2);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public static void paralel(int c,int p, int pR) {

        Thread[] readers = new Thread[pR];
        Thread[] consumers = new Thread[p-pR];
        ThreadSafeClasament clasament = new ThreadSafeClasament();
        ThreadSafeQueue queue = new ThreadSafeQueue(pR);

        int chunkSize = c / pR;

        for(int i = 0 ; i < pR ; i ++){
            int threadIndex = i;
            readers[i] = new Thread(() -> {
                int start = (threadIndex+1) *chunkSize -1;
                int end;
                if(threadIndex == pR - 1)
                    end = c + 1;
                else
                    end = start + chunkSize;
                String folderPath = "Rezultate";
                File folder = new File(folderPath);

                for(int tara = start ; tara < end ; tara ++){
                    for (int problema = 1; problema <= 10; problema++) {

                        String fileName = "C" + tara + "Problema" + problema + ".txt";
                        File file = new File(folder, fileName);
                        Scanner scanner = null;
                        try {
                            scanner = new Scanner(file);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            String[] tokens = line.split(",");
                            if(tokens.length == 2) {
                                Integer ID = Integer.valueOf(tokens[0]);
                                Integer punctaj = Integer.valueOf(tokens[1]);
                                try {
                                    queue.produce(new Pereche(ID, punctaj));
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
                queue.finish();
            });

            readers[i].start();
        }

        for(int i = 0 ; i < p - pR ; i ++){
            consumers[i] = new Thread(() -> {
                while (queue.hasValues()){
                    try {
                        Pereche pereche = queue.consume();
                        if(pereche != null)
                            clasament.add(pereche);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            });

            consumers[i].start();
        }


        for(int i = 0 ; i < pR ; i ++){
            try {
                readers[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(int i = 0 ; i < p-pR ; i ++){
            try {
                consumers[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try (FileWriter writer = new FileWriter("ClasamentParalel.csv")) {
            writer.write("Concurent,Punctaj" + System.lineSeparator());
            for (Pereche concurent : clasament.getClasament()) {
                writer.write(concurent.getFirst() + "," + concurent.getSecond() + System.lineSeparator());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void generazaFisiere(int c) {
        int ID = 0;
        Random rand = new Random();

        String folderPath = "Rezultate";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created: " + folderPath);
            } else {
                System.out.println("Failed to create folder: " + folderPath);
                return;
            }
        }

        for (int i = 1; i <= c; i++) {
            int nrConcurenti = rand.nextInt(80, 100);

            Map<Integer, FileWriter> problemWriters = new HashMap<>();
            try {
                for (int problema = 1; problema <= 10; problema++) {
                    String fileName = "C" + i + "Problema" + problema + ".txt";
                    File file = new File(folder, fileName);
                    problemWriters.put(problema, new FileWriter(file));
                }

                for (int concurent = 0; concurent < nrConcurenti; concurent++, ID++) {
                    for (int problema = 1; problema <= 10; problema++) {
                        double r = rand.nextDouble();

                        int punctaj;
                        if (r < 0.10) {
                            punctaj = 0;
                        } else if (r < 0.12) {
                            punctaj = -1;
                        } else {
                            punctaj = rand.nextInt(10) + 1;
                        }

                        FileWriter writer = problemWriters.get(problema);
                        writer.write(ID + "," + punctaj + System.lineSeparator());
                    }
                }
            } catch (IOException e) {
                 e.printStackTrace();
            } finally {
                for (FileWriter writer : problemWriters.values()) {
                    try {
                        if (writer != null) {
                            writer.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void secvential(int c) throws FileNotFoundException {
        Set<Integer> ingrati = new TreeSet<>();
        Map<Integer, Integer> punctaje = new HashMap<>();

        Queue<Pereche> perechi = new LinkedList<>();

        String folderPath = "Rezultate";
        File folder = new File(folderPath);

        for (int tara = 1; tara <= c; tara++) {
            for (int problema = 1; problema <= 10; problema++) {

                String fileName = "C" + tara + "Problema" + problema + ".txt";
                File file = new File(folder, fileName);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split(",");
                    if(tokens.length == 2) {
                        Integer ID = Integer.valueOf(tokens[0]);
                        Integer punctaj = Integer.valueOf(tokens[1]);
                        perechi.add(new Pereche(ID, punctaj));
                    }
                }
            }
        }

        for (Pereche pereche : perechi) {
            if (pereche.getSecond() == -1) {
                ingrati.add(pereche.getFirst());
                punctaje.remove(pereche.getFirst());
            } else {
                if (!ingrati.contains(pereche.getFirst())) {
                    if (punctaje.containsKey(pereche.getFirst())) {
                        int old = punctaje.get(pereche.getFirst());
                        punctaje.put(pereche.getFirst(), old + pereche.getSecond());
                    } else {
                        punctaje.put(pereche.getFirst(), pereche.getSecond());
                    }
                }
            }
        }

        Set<Pereche> clasament = new TreeSet<>();

        for (Integer ID : punctaje.keySet()) {
            clasament.add(new Pereche(ID, punctaje.get(ID)));
        }


        try (FileWriter writer = new FileWriter("ClasamentSecvential.csv")) {
            writer.write("Concurent,Punctaj" + System.lineSeparator());
            for (Pereche concurent : clasament) {
                writer.write(concurent.getFirst() + "," + concurent.getSecond() + System.lineSeparator());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}