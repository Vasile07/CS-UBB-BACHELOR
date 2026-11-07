public class Robot extends Thread {
    private final Integer threadId;
    private final RobotType type;
    private final BandaTransportoare banda;
    private final Integer waitTime;
    private final Integer numberOfIterations;
    private final ListaOperatii listaOperatii;

    public Robot(Integer threadId, RobotType type, BandaTransportoare banda, Integer waitTime, Integer numberOfIterations, ListaOperatii listaOperatii) {
        this.threadId = threadId;
        this.type = type;
        this.banda = banda;
        this.waitTime = waitTime;
        this.numberOfIterations = numberOfIterations;
        this.listaOperatii = listaOperatii;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfIterations; i++) {
            switch (type) {
                case CONSUMER -> {
                    synchronized (banda) {
                        banda.consume(3);
                        listaOperatii.addOperatie(new Operatie(threadId, "Preia", banda.lenght()));
                    }
                }
                case PRODUCER -> {
                    synchronized (banda) {
                        banda.produce(4);
                        listaOperatii.addOperatie(new Operatie(threadId, "Depune", banda.lenght()));
                    }
                }
            }
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
