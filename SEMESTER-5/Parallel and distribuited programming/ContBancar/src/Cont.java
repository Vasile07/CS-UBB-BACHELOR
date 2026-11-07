public class Cont {
    private Integer lei;
    private Integer euro;
    private final Object leiLock = new Object();
    private final Object euroLock = new Object();

    public Cont() {
        lei = 0;
        euro = 0;
    }

    public void depuneLei(Integer suma){
        synchronized (leiLock) {
            lei += suma;
            leiLock.notifyAll();
        }
    }


    public void depuneEuro(Integer suma){
        synchronized (euroLock) {
            euro += suma;
            euroLock.notifyAll();
        }
    }


    public void retrageLei(Integer suma){
        synchronized (leiLock) {
            if (lei < suma){
                throw new RuntimeException("Sold lei insuficient");
            }
            lei -= suma;
            leiLock.notifyAll();
        }
    }


    public void retrageEuro(Integer suma){
        synchronized (euroLock) {

            if (euro < suma){
                throw new RuntimeException("Sold euro insuficient");
            }
            euro -= suma;
            euroLock.notifyAll();
        }
    }

    public Integer soldLei(){
        synchronized (leiLock){
            return lei;
        }
    }

    public Integer soldEuro(){
        synchronized (euroLock){
            return euro;
        }
    }
}
