import java.util.Objects;

public class Operatie {
    private Integer threadId;
    private String tipOperatie;
    private Integer lenght;
    public Operatie(Integer threadId, String tipOperatie, Integer lenght) {
        this.lenght = lenght;
        this.tipOperatie = tipOperatie;
        this.threadId = threadId;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public String getTipOperatie() {
        return tipOperatie;
    }

    public void setTipOperatie(String tipOperatie) {
        this.tipOperatie = tipOperatie;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operatie operatie = (Operatie) o;
        return Objects.equals(threadId, operatie.threadId) && Objects.equals(tipOperatie, operatie.tipOperatie) && Objects.equals(lenght, operatie.lenght);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threadId, tipOperatie, lenght);
    }

    @Override
    public String toString() {
        return "Operatie{" +
                "threadId=" + threadId +
                ", tipOperatie='" + tipOperatie + '\'' +
                ", lenght=" + lenght +
                '}';
    }
}
