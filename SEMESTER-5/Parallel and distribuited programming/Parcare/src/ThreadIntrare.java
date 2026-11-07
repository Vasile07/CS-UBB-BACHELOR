public class ThreadIntrare extends Thread{
    private final Parcare parcare;

    private final Integer nrIteratii;

    private final ListaOperatii listaOperatii;

    public ThreadIntrare(Parcare parcare, Integer nrIteratii, ListaOperatii listaOperatii) {
        this.parcare = parcare;
        this.nrIteratii = nrIteratii;
        this.listaOperatii = listaOperatii;
    }

    @Override
    public void run() {
        for (int i = 0; i < nrIteratii; i++){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            parcare.intrare();
            listaOperatii.addOperatie("intrare");
        }
    }
}
