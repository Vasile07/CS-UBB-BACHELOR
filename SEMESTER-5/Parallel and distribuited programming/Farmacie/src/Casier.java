import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Casier extends Thread{
    private final CoadaCasierie coadaCasierie;

    public Casier(CoadaCasierie coadaCasierie) {
        this.coadaCasierie = coadaCasierie;
    }

    @Override
    public void run() {
        Inregistrare inregistrare = null;
        do{
            inregistrare = coadaCasierie.dequeue();
            if (inregistrare != null){
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("inregistrari.txt"))){
                    writer.write(inregistrare.getNrFactura() + "," + inregistrare.getValoare() + "," + inregistrare.getCodReteta());
                    writer.newLine();
                }catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("Reteta nr " + inregistrare.getCodReteta() + " a fost platita si preluata.");
            }
        }while (inregistrare != null);
    }
}
