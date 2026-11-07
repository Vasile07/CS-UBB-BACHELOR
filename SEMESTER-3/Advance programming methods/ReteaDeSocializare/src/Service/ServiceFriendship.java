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

public class ServiceFriendship {
    private AbstractRepository<Pair<Long,Long>, Friendship> friendshipRepository;
    private AbstractRepository<Long, User> userRepository;

    public ServiceFriendship(AbstractRepository<Pair<Long, Long>, Friendship> friendshipRepository, AbstractRepository<Long, User> userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    /**
     * Add a friendship between u1 and u2 and between u2 and u1
     * @param idUser1 Long
     * @param idUser2 Long
     */
    public void addFriendship(Long idUser1, Long idUser2){
        User user1 = userRepository.findByID(idUser1);
        User user2 = userRepository.findByID(idUser2);
        Friendship friendshipU1toU2 = new Friendship(user1, user2);
        Friendship friendshipU2toU1 = new Friendship(user2, user1);
        friendshipU1toU2.setId(new Pair<>(idUser1,idUser2));
        friendshipU2toU1.setId(new Pair<>(idUser2,idUser1));
        friendshipRepository.add(friendshipU1toU2);
        user1.addFriend(user2);
        friendshipRepository.add(friendshipU2toU1);
        user2.addFriend(user1);
    }

    /**
     * Remove the friendship between u1 and u2 and between u2 and u1
     * @param idUser1 Long
     * @param idUser2 Long
     */
    public void removeFriendshipById(Long idUser1, Long idUser2){
        friendshipRepository.deleteByID(new Pair<>(idUser1,idUser2));
        friendshipRepository.deleteByID(new Pair<>(idUser2,idUser1));
    }

    /**
     * Return al friendships
     * @return All friendships
     */
    public Iterable<Friendship> getAllFriendships(){
        return friendshipRepository.getAll();
    }


    /**
     * DFS
     * @param u User
     * @param visitedUsers HashMap<User,Boolean>
     * @param L List<User>
     */
    private void Visited(User u, HashMap<User, Boolean> visitedUsers, List<User> L){
        if(!visitedUsers.get(u)) {
            visitedUsers.put(u, true);

            for (User friend : u.getFriends()) {
                Visited(friend,visitedUsers,L);
            }
            L.add(u);
        }
    }

    /**
     * Assign all the friends to the u community
     * @param u User
     * @param comunityNumber Integer
     * @param Comunity Map<User,Integer>
     */
    private void Assign(User u, Integer comunityNumber, Map<User,Integer> Comunity){
        if(!Comunity.containsKey(u)){
            Comunity.put(u,comunityNumber);
            for(User friend : u.getFriends())
                Assign(friend,comunityNumber,Comunity);
        }
    }

    /**
     * Returneaza toate comunitatile si useri din ele
     * @return Map<Integer,List<User>>
     */
    private Map<Integer, List<User>> comunitati(){

        HashMap<User,Boolean> visitedUsers = new HashMap<>();
        List<User> listOfUsers = new ArrayList<>();
        for(User user : userRepository.getAll()){
            visitedUsers.put(user,false);
        }

        for(User user : userRepository.getAll()){
            Visited(user, visitedUsers, listOfUsers);
        }

        Map<User,Integer> Comunity = new HashMap<>();
        Integer comunityNumber = 1;
        for ( User u : listOfUsers){
            Assign(u,comunityNumber,Comunity);
            comunityNumber++;
        }


        Map<Integer, List<User>> AllComunities = new HashMap<>();
        for(User user : Comunity.keySet()){
            Integer c = Comunity.get(user);
            if(!AllComunities.containsKey(c)){
                AllComunities.put(c, new ArrayList<>());
                AllComunities.get(c).add(user);
            }else{
                AllComunities.get(c).add(user);
            }
        }

        return AllComunities;
    }

    /**
     * Returneaza numarul de comunitati
     * @return Integer
     */
    public Integer numberOfCommuitties(){
        return comunitati().size();
    }

    /**
     * Return the list with the user from the most sociable community
     * @return List<User>
     */
    public List<User> mostSociableComunitty(){
        Map<Integer,List<User>> comunitty = comunitati();
        List<User> mSCList = new ArrayList<>();
        for(Integer nrCom : comunitty.keySet())
            if( comunitty.get(nrCom).size() > mSCList.size())
                mSCList = comunitty.get(nrCom);
        return mSCList;
    }

}
