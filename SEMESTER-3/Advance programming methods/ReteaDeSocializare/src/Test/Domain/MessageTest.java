package Test.Domain;

import Domain.Message;
import Domain.User;

import java.util.Objects;

public class MessageTest implements DomainTest {

    private final User u1 = new User("A","AA");
    private final User u2 = new User("B","BB");
    private final String msg = "Salut";
    @Override
    public void createTest() {
        Message message = new Message(u1,msg,u2);
        assert Objects.equals(message.getFrom(), u1);
        assert Objects.equals(message.getMsg(), msg);
        assert Objects.equals(message.getTo(), u2);
    }

    @Override
    public void setTest() {
        Message message = new Message(u1,msg,u2);
        User newU1 = new User("C","CC");
        User newU2 = new User("D","DD");
        String newMsg = "Pa";
        message.setFrom(newU1);
        message.setMsg(newMsg);
        message.setTo(newU2);
        assert Objects.equals(message.getFrom(), newU1);
        assert Objects.equals(message.getMsg(), newMsg);
        assert Objects.equals(message.getTo(), newU2);
    }
}