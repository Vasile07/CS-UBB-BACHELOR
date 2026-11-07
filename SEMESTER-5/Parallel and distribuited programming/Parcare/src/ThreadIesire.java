public class ThreadIesire extends Thread{
    private final Parcare parcare;

    private final Integer nrIteratii;

    private final ListaOperatii listaOperatii;

    public ThreadIesire(Parcare parcare, Integer nrIteratii, ListaOperatii listaOperatii) {
        this.parcare = parcare;
        this.nrIteratii = nrIteratii;
        this.listaOperatii = listaOperatii;
    }

    @Override
    public void run() {
        for (int i = 0; i < nrIteratii; i++){
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            parcare.iesire();
            listaOperatii.addOperatie("iesire");
        }
    }
}
