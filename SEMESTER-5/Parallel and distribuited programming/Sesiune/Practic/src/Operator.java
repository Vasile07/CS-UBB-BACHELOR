import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Operator extends Thread{
    private CoadaCuCapacitate coadaPrioritate1;
    private CoadaCuCapacitate coadaPrioritate2;

    private Integer NO;
    private Integer TO;

    private Integer idOperator;

    public Operator(CoadaCuCapacitate coadaPrioritate1, CoadaCuCapacitate coadaPrioritate2, Integer NO, Integer to, Integer idOperator) {
        this.coadaPrioritate1 = coadaPrioritate1;
        this.coadaPrioritate2 = coadaPrioritate2;
        this.NO = NO;
        TO = to;
        this.idOperator = idOperator;
    }

    @Override
    public void run() {
        try {
            File file = new File("input.txt");
            Scanner reader = new Scanner(file);

            for(int i = 0 ; i < idOperator*NO ; i ++)
                if(reader.hasNextLine())
                    reader.nextLine();

            int i = 0 ;
            while (reader.hasNextLine() && i < NO) {

                Thread.sleep(TO);

                String line = reader.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Comanda comanda = new Comanda(Math.toIntExact(this.threadId()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                    if(comanda.getPrioritate() == 1){
                        coadaPrioritate1.add(comanda);
                    }else{
                        coadaPrioritate2.add(comanda);
                    }
                }

                i++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
