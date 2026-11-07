public class Iterator extends Thread{
    private final SalaSpectacol salaSpectacol;
    private final Integer sleepTime;

    private volatile boolean running = true;

    public Iterator(SalaSpectacol salaSpectacol,Integer sleepTime) {
        this.salaSpectacol = salaSpectacol;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (running){
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (salaSpectacol) {
                String[][] sala = salaSpectacol.iterare();
                for (int i = 0; i < sala.length; i++) {
                    for (int j = 0; j < sala[0].length; j++)
                        System.out.print(sala[i][j] + " ");
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

    public void stopIterator(){
        running = false;
    }
}
