import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Manager extends Thread{
    private  CoadaCuCapacitate coadaPrioritate1;
    private  CoadaCuCapacitate coadaPrioritate2;
    private Integer Tm;

    private Dictionar dictionar;

    private volatile boolean running = true;

    public Manager(CoadaCuCapacitate coadaPrioritate1, CoadaCuCapacitate coadaPrioritate2, Integer tm, Dictionar dictionar) {
        this.coadaPrioritate1 = coadaPrioritate1;
        this.coadaPrioritate2 = coadaPrioritate2;
        Tm = tm;
        this.dictionar = dictionar;
    }

    @Override
    public void run() {
        while (running){

            try {
                Thread.sleep(Tm);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

                    Integer numarComenzi1 = coadaPrioritate1.getNumarComenzi();
                    LocalDateTime dateTime1 = LocalDateTime.now();
                    System.out.println("Coada cu prioritate 1 : " + numarComenzi1 + " " + dateTime1);

                    Integer numarComenzi2 = coadaPrioritate2.getNumarComenzi();
                    LocalDateTime dateTime2 = LocalDateTime.now();
                    System.out.println("Coada cu prioritate 2 : " + numarComenzi2 + " " + dateTime2);

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            Map<Integer, List<Integer>> comenzi = dictionar.getComenzi();
            for(Integer key : comenzi.keySet()){
                String string = key + " " + comenzi.get(key);
                writer.write(string);
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopRunning(){
        running = false;
    }
}
