import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Pacient extends Thread{
    private final Coada coada;

    public Pacient(Coada coada) {
        this.coada = coada;
    }

    @Override
    public void run() {
        Random random = new Random();
        Integer nrMedicamente = random.nextInt(1, 6);
        List<Medicament> medicamentList = new ArrayList<>();
        List<Medicament> medicamente = new ArrayList<>();
        File file = new File("medicamente.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 2){
                Integer codMedicament = Integer.valueOf(parts[0]);
                Integer pretMedicament = Integer.valueOf(parts[1]);
                Medicament medicament = new Medicament(codMedicament, pretMedicament);
                medicamente.add(medicament);
            }
        }

        for (int i = 0; i < nrMedicamente; i++){
            int randomIndex = random.nextInt(medicamente.size());
            Medicament med = medicamente.get(randomIndex);
            medicamentList.add(med);
        }

        Reteta reteta = new Reteta(null, nrMedicamente, medicamentList);
        coada.add(reteta);
    }
}
