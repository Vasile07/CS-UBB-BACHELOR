public class MembruEchipaDeImpachetare extends Thread{

    private CoadaCuCapacitate coadaPrioritate1;
    private CoadaCuCapacitate coadaPrioritate2;
    private Integer TI;

    private Dictionar dictionar;

    public MembruEchipaDeImpachetare(CoadaCuCapacitate coadaPrioritate1, CoadaCuCapacitate coadaPrioritate2, Integer ti, Dictionar dictionar) {
        this.coadaPrioritate1 = coadaPrioritate1;
        this.coadaPrioritate2 = coadaPrioritate2;
        TI = ti;
        this.dictionar = dictionar;
    }

    @Override
    public void run() {
        Comanda comanda = null;
        do{
            comanda = coadaPrioritate2.dequeue();
            if(comanda == null)
                comanda = coadaPrioritate1.dequeue();

            if(comanda != null){
                try {
                    Thread.sleep(TI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                dictionar.add(comanda);
            }
        }while (comanda != null);
    }
}
