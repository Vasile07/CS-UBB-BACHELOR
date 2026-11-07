import java.util.Objects;

public class SalaSpectacol {
    private final String[][] sala;
    private final Object lock = new Object();

    public SalaSpectacol(int numarRanduri, int numarLocuriPerRand) {
        sala = new String[numarRanduri][numarLocuriPerRand];
        for (int rand = 0; rand < numarRanduri; rand++) {
            for (int loc = 0; loc < numarLocuriPerRand; loc++) {
                sala[rand][loc] = "O";
            }
        }
    }

    public void inserare(int identificatorRand, int identificatorLoc) {
        synchronized (lock) {
            if (Objects.equals(sala[identificatorRand][identificatorLoc], "O"))
                sala[identificatorRand][identificatorLoc] = "X";
            else
                notify();
        }
    }

    public String[][] iterare() {
        synchronized (lock) {
            String[][] copieSala = new String[sala.length][sala[0].length];
            for (int i = 0; i < sala.length; i++) {
                for (int j = 0; j < sala[0].length; j++) {
                    copieSala[i][j] = sala[i][j];
                }
            }
            return copieSala;
        }
    }
}
