package Test.Domain;

import Domain.Friendship;
import Domain.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class FriendshipTest implements DomainTest{

    private final User u1 = new User("A","AA");
    private final User u2 = new User("B","BB");
    @Override
    public void createTest() {
        Friendship friendship = new Friendship(u1,u2);
        assert Objects.equals(friendship.getU1(), u1);
        assert Objects.equals(friendship.getU2(), u2);
        assert Objects.equals(friendship.getDate(), LocalDateTime.now());
    }

    @Override
    public void setTest() {

    }
}
