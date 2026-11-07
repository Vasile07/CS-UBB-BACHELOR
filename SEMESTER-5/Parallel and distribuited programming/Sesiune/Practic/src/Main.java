import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int O = 3;
        int No = 10;
        int To = 15;
        int L = 4;
        int Tl = 200;
        int Tm = 40;

        CoadaCuCapacitate coadaPrioritate1 = new CoadaCuCapacitate(30);
        CoadaCuCapacitate coadaPrioritate2 = new CoadaCuCapacitate(30);
        Dictionar dictionar = new Dictionar();

        genereazaComenzi(O, No);

        Thread[] operatori = new Thread[O];
        Thread[] membriEchipeImpachetare = new Thread[L];
        Manager manager = new Manager(coadaPrioritate1, coadaPrioritate2, Tm,dictionar);

        manager.start();

        for(int i = 0 ; i < O ; i ++){
            operatori[i]  = new Operator(coadaPrioritate1, coadaPrioritate2, No, To, i);
        }

        for(int i = 0 ; i < L ; i ++){
            membriEchipeImpachetare[i]  = new MembruEchipaDeImpachetare(coadaPrioritate1, coadaPrioritate2, Tl, dictionar);
        }

        for(int i = 0 ; i < O ; i ++){
            operatori[i].start();
        }

        for(int i = 0 ; i < L ; i ++){
            membriEchipeImpachetare[i].start();
        }


        for(int i = 0 ; i < O ; i ++){
            operatori[i].join();
        }

        coadaPrioritate1.finishReading();
        coadaPrioritate2.finishReading();

        for(int i = 0 ; i < L ; i ++){
            membriEchipeImpachetare[i].join();
        }


        manager.stopRunning();
        manager.join();
    }

    private static void genereazaComenzi(int o, int no) {
        Random random = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"))) {
            for (int k = 0; k < o*no; k++) {
                Integer prioritate = random.nextInt(1, 2+ 1);
                writer.write(k + "," + prioritate);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}