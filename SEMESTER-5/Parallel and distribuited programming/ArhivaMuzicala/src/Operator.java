public class Operator extends Thread{
    private final Coada coada;

    private final MapTitlu mapTitlu;
    private final MapCompozitor mapCompozitor;

    public Operator(Coada coada, MapTitlu mapTitlu, MapCompozitor mapCompozitor) {
        this.coada = coada;
        this.mapTitlu = mapTitlu;
        this.mapCompozitor = mapCompozitor;
    }

    @Override
    public void run() {
        InregistrareMuzicala inregistrareMuzicala = null;
        do{
            inregistrareMuzicala = coada.dequeue();
            if(inregistrareMuzicala != null){
                mapCompozitor.addToMap(inregistrareMuzicala);
                mapTitlu.addToMap(inregistrareMuzicala);
            }
        }while (inregistrareMuzicala != null);
    }
}
