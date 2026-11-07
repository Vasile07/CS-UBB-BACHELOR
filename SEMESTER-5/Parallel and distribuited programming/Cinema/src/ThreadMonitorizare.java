public class ThreadMonitorizare extends Thread{
    private final SalaSpectacol salaSpectacol;

    private volatile boolean running = true;
    public ThreadMonitorizare(SalaSpectacol salaSpectacol) {
        this.salaSpectacol = salaSpectacol;
    }

    @Override
    public void run() {
        synchronized (salaSpectacol) {
            while (running) {
                try {
                    salaSpectacol.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Ne pare rau, locul a fost deja achizitionat");
            }
        }
    }

    public void stopMonitorizare(){
        running = false;
        synchronized (salaSpectacol) {
            salaSpectacol.notify();
        }
    }
}
