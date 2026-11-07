
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 3;
        int numarTranzactii = 20;
        ListaTranzactii listaTranzactii = new ListaTranzactii();
        Cont cont = new Cont();
        Thread[] membrii = new Thread[n];

        Iterator iterator = new Iterator(listaTranzactii);
        for(int i = 0 ; i < n ; i ++){
            membrii[i] = new Membru(i, numarTranzactii, listaTranzactii, cont);
        }

        iterator.start();
        for(int i = 0 ; i < n ; i ++)
            membrii[i].start();


        for(int i = 0 ; i < n ; i ++)
            membrii[i].join();

        iterator.stopIterator();
        synchronized (listaTranzactii) {
            listaTranzactii.notifyAll();
        }

        System.out.println("Sold final lei: %d".formatted(cont.soldLei()));
        System.out.println("Sold final euro: %d".formatted(cont.soldEuro()));
    }
}