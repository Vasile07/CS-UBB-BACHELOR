
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 100;
        int p = 3;
        int c = 2;

        BandaTransportoare banda = new BandaTransportoare(n);
        int waitProduce = 10;
        int waitConsume = 8;
        int numberOfIterations = 10;
        ListaOperatii listaOperatii = new ListaOperatii();
        Thread[] producers = new Thread[p];
        Thread[] consumers = new Thread[c];

        Iterator iterator = new Iterator(listaOperatii);
        iterator.start();

        for(int i = 0 ; i < p ; i ++){
            producers[i] = new Robot(i, RobotType.PRODUCER, banda, waitProduce, numberOfIterations, listaOperatii);
            producers[i].start();
        }


        for(int i = 0 ; i < c ; i ++){
            consumers[i] = new Robot(i, RobotType.CONSUMER, banda, waitConsume, numberOfIterations, listaOperatii);
            consumers[i].start();
        }

        for(int i = 0 ; i < p ; i ++){
            producers[i].join();
        }

        for(int i = 0 ; i < c ; i ++){
            consumers[i].join();
        }

        iterator.stopIterator();
    }
}