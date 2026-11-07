import Domain.Friendship;
import Domain.Pair;
import Domain.User;
import Persistance.AbstractRepository;
import Persistance.InMemoryRepository;
import Presentation.ConsoleUI;
import Service.ServiceFriendship;
import Service.ServiceUsers;
import Test.Test;
import Validator.FriendshipValidator;
import Validator.UserValidator;

public class Main {
    public static void main(String[] args) {
        Test test = new Test();
        test.runAllTests();
//        System.out.println("Teste trecute cu succes!");
        UserValidator userValidator = new UserValidator();
        FriendshipValidator friendshipValidator = new FriendshipValidator();
        AbstractRepository<Long,User> repoUser = new InMemoryRepository<>(userValidator);
        AbstractRepository<Pair<Long,Long>,Friendship> repoFriendship = new InMemoryRepository<>(friendshipValidator);
        ServiceUsers serviceUsers = new ServiceUsers(repoUser,repoFriendship);
        ServiceFriendship serviceFriendship = new ServiceFriendship(repoFriendship,repoUser);
        ConsoleUI consoleUI = new ConsoleUI(serviceUsers,serviceFriendship);
        consoleUI.runUI();
    }
}