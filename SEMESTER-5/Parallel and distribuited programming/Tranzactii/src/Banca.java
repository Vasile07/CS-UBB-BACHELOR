import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Banca extends Thread{
    private final Coada coada;

    private Dictionar<String, Valoare> dictionar;

    public Banca(Coada coada) {
        this.coada = coada;
        this.dictionar = new Dictionar<>();
    }

    @Override
    public void run() {
        Tranzactie tranzactie = null;
        do{
            tranzactie = coada.dequeue();
            if (tranzactie != null){
                String client = tranzactie.getClient();
                String tip = tranzactie.getTip();
                Integer suma = tranzactie.getSuma();
                Valoare valoare = new Valoare(tip, suma);
                dictionar.pushValue(client, valoare);

                try(BufferedWriter writer = new BufferedWriter(new FileWriter("tranzactii.log", true))){
                    writer.write(tranzactie.toString());
                    writer.newLine();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }while (tranzactie != null);
    }
}
