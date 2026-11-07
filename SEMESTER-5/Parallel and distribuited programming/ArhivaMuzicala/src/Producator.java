import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Producator extends Thread{
    private final Coada coada;
    private final List<String> numeFisiere;

    public Producator(Coada coada, List<String> numeFisiere) {
        this.coada = coada;
        this.numeFisiere = numeFisiere;
    }

    @Override
    public void run() {
        for(String numeFisier : numeFisiere){
            try {
                File file = new File(numeFisier);
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()){
                    String line = reader.nextLine();
                    String[] parts = line.split(",");
                    if(parts.length == 3){
                        InregistrareMuzicala inregistrareMuzicala = new InregistrareMuzicala(parts[0],parts[1],Departament.valueOf(parts[2]));
                        synchronized (coada) {
                            coada.add(inregistrareMuzicala);
                        }
                    }else{
                        throw new RuntimeException("Fisierul %s nu are linia %s formatata bine".formatted(numeFisier, line));
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
