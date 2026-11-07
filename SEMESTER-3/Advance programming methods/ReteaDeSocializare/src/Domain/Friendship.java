package Domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Friendship extends Entity<Pair<Long, Long>> {
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private User u1;
    private User u2;
    private LocalDateTime date;

    /**
     * Create a friendship between user 1 and user 2
     * @param u1 User
     * @param u2 User
     * @param date LocalDateTime
     */
    public Friendship(User u1, User u2, LocalDateTime date) {
        this.u1 = u1;
        this.u2 = u2;
        this.date = date;
    }

    /**
     * Create a friendship between u1 and u2 with date = now
     * @param u1 User
     * @param u2 User
     */
    public Friendship(User u1, User u2) {
        this.u1 = u1;
        this.u2 = u2;
        this.date = LocalDateTime.now();
    }

    /**
     * Return user 1
     * @return User
     */
    public User getU1() {
        return u1;
    }

    /**
     * Return user 2
     * @return User
     */
    public User getU2() {
        return u2;
    }

    /**
     * Return date
     * @return LocalDateTime
     */
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Friendship{ " +
                u1 +
                " ; " + u2 +
                " ; date= " + date.format(DATE_FORMATTER) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(u1, that.u1) && Objects.equals(u2, that.u2) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), u1, u2, date);
    }
}
