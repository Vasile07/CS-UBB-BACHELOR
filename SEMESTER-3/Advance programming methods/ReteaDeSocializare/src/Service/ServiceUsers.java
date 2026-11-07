package Service;

import Domain.Friendship;
import Domain.Pair;
import Domain.User;
import Persistance.AbstractRepository;
import Persistance.InMemoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceUsers {
    AbstractRepository<Long, User> userRepostiory;
    AbstractRepository<Pair<Long,Long>, Friendship> friendshipRepository;

    public ServiceUsers(AbstractRepository<Long, User> userRepostiory, AbstractRepository<Pair<Long, Long>, Friendship> friendshipRepository) {
        this.userRepostiory = userRepostiory;
        this.friendshipRepository = friendshipRepository;
    }

    /**
     * Add the user
     * @param name String
     * @param password String
     */
    public void addUser(String name, String password){
        User user = new User(name, password);
        user.setId(Long.valueOf(userRepostiory.numberOfEntities()) + 1);
        userRepostiory.add(user);
    }

    /**
     * Remove the user
     * @param id Long
     */
    public void removeUserById(Long id){
        User user = userRepostiory.deleteByID(id);
        for(User friend : user.getFriends()){
            Long friendId = friend.getId();
            Long userId = user.getId();
            friendshipRepository.deleteByID(new Pair<>(userId,friendId));
            friendshipRepository.deleteByID(new Pair<>(friendId,userId));
        }
    }

    /**
     * Return all users
     * @return All users
     */
    public Iterable<User> getAllUsers(){
        return userRepostiory.getAll();
    }

}