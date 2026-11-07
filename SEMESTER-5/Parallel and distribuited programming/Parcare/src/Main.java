// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 100;
        int p = 25;
        int nrIntrari = 3;
        int nrIesiri = 2;
        int nrIteratiiIntrari = 200;
        int nrIteratiiIesiri = 275;

        Parcare parcare = new Parcare(n, p);
        ListaOperatii listaOperatii = new ListaOperatii();

        Thread[] intrari = new Thread[nrIntrari];
        Thread[] iesiri = new Thread[nrIesiri];

        Iterator iterator = new Iterator(listaOperatii, p, n);
        iterator.start();

        for (int i = 0; i < nrIntrari; i++){
            intrari[i] = new ThreadIntrare(parcare, nrIteratiiIntrari, listaOperatii);
            intrari[i].start();
        }

        for (int i = 0; i < nrIesiri; i++){
            iesiri[i] = new ThreadIesire(parcare, nrIteratiiIesiri, listaOperatii);
            iesiri[i].start();
        }

        for (int i = 0; i < nrIntrari; i++){
            intrari[i].join();
        }

        for (int i = 0; i < nrIesiri; i++){
            iesiri[i].join();
        }

        iterator.stopIterator();
        iterator.join();
    }
}