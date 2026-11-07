package Domain;

import java.util.Objects;

public class Message extends Entity<Long> {
    private User from;
    private String msg;
    private User to;

    public Message(User from, String msg, User to) {
        this.from = from;
        this.msg = msg;
        this.to = to;
    }

    public User getFrom() {
        return from;
    }

    public String getMsg() {
        return msg;
    }

    public User getTo() {
        return to;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTo(User to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Message message = (Message) o;
        return Objects.equals(from, message.from) && Objects.equals(msg, message.msg) && Objects.equals(to, message.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, msg, to);
    }

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", msg='" + msg + '\'' +
                ", to=" + to +
                '}';
    }
}

