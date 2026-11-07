package Test.Domain;

import Domain.User;

import java.util.Objects;

public class UsetTests implements DomainTest{

    private final String name = "Paul Rus";
    private final String password = "paulRus22Agweascetxcqr";
    private final Long id = 123L;
    @Override
    public void runAllTests() {
        DomainTest.super.runAllTests();
        getFriendsTest();
    }

    private void getFriendsTest() {
        User friend1 = new User("A","A");
        User friend2 = new User("B","B");
        User friend3 = new User("C","C");

        User user = new User(name, password);

        assert user.getFriends().isEmpty();

        user.addFriend(friend1);
        user.addFriend(friend2);
        user.addFriend(friend3);

        assert user.getFriends().contains(friend1) && user.getFriends().contains(friend2) && user.getFriends().contains(friend3);

    }

    @Override
    public void createTest() {
        User user = new User(name, password);
        user.setId(id);
        assert Objects.equals(user.getName(), name);
        assert Objects.equals(user.getPassword(), password);
        assert Objects.equals(user.getId(), id);
    }

    @Override
    public void setTest() {
        User user = new User(name, password);
        user.setId(id);
        String newName = "ABc";
        String newPassword = "adaaad";
        Long newId = 12L;
        user.setName(newName);
        user.setPassword(newPassword);
        user.setId(newId);

        assert Objects.equals(user.getName(), newName);
        assert Objects.equals(user.getPassword(), newPassword);
        assert Objects.equals(user.getId(), newId);
    }
}
