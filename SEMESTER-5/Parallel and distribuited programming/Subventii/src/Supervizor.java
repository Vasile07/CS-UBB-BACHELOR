import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Supervizor extends Thread {

    private AtomicInteger fonduriCurente;

    private final Object lock;

    public Supervizor(AtomicInteger fonduriCurente, Object lock) {
        this.fonduriCurente = fonduriCurente;
        this.lock = lock;
    }

    @Override
    public void run() {
        File file = new File("cereri.txt");
        Integer suma = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    if (Objects.equals(parts[2], "acceptata")) {
                        suma += Integer.parseInt(parts[1]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        synchronized (lock) {
            if (suma <= fonduriCurente.get()) {
                Random random = new Random();
                int val = random.nextInt(100, 201);
                System.out.println("VERIFICARE VALIDA: Supervizorul a verificat ca valoarea fondurilor alocate " + fonduriCurente + ", este mai mare decat suma tuturor cererilor acceptate " + suma + " si a ajustat fondurile adaugand " + val + " RON");
                fonduriCurente.addAndGet(val);
            } else {
                Random random = new Random();
                int val = random.nextInt(100, 201);
                System.out.println("VERIFICARE INVALIDA: Supervizorul a verificat ca valoarea fondurilor alocate " + fonduriCurente + ", este mai mica decat suma tuturor cererilor acceptate " + suma + " si a ajustat fondurile adaugand " + val + " RON");
                fonduriCurente.addAndGet(val);
            }
        }
    }

}

