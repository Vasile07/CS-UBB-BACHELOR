import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Angajat extends Thread {
    private final Coada coada;

    private AtomicInteger fonduriCurente;

    private Integer sumaAlocata;

    private final Object lock;

    private final Object lockFonduriCurente;

    public Angajat(Coada coada, AtomicInteger fonduriCurente, Integer sumaAlocata, Object lock, Object lockFonduriCurente) {
        this.coada = coada;
        this.fonduriCurente = fonduriCurente;
        this.sumaAlocata = sumaAlocata;
        this.lock = lock;
        this.lockFonduriCurente = lockFonduriCurente;
    }

    @Override
    public void run() {
        Cerere cerere = null;
        do {
            cerere = coada.dequeue();
            synchronized (lockFonduriCurente) {
                synchronized (lock) {
                    if (cerere != null) {
                        System.out.println("Cererea cu nr " + cerere.getCod() + " a fost preluata de catre angajatul " + this.threadId());
                        Integer cod = cerere.getCod();
                        Integer valoare = cerere.getValoare();
                        String status;
                        if (valoare + sumaAlocata <= fonduriCurente.get()) {
                            sumaAlocata += valoare;
                            status = "acceptata";
                            System.out.println("Cererea cu nr " + cerere.getCod() + "a fost acceptata de catre angajatul " + this.threadId() + " deoarece valoarea ceruta " + valoare + " poate fi onorata fondurilor disponibile " + fonduriCurente);
                        } else {
                            status = "refuzata";
                            System.out.println("Cererea cu nr " + cerere.getCod() + " nu a fost acceptata de catre angajatul " + this.threadId() + " deoarece valoarea ceruta " + valoare + " depaseste fondurile disponibile " + fonduriCurente);
                        }
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cereri.txt", true))) {
                            writer.write(cod + "," + valoare + "," + status + "," + fonduriCurente);
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } while (cerere != null);
    }
}
