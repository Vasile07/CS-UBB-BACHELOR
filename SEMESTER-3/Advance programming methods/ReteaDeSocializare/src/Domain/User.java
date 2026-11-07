package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class User extends Entity<Long> {
    private String name;
    private String password;
    private List<User> friends;


    /**
     * Create the user
     * @param name String
     * @param password String
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        friends = new ArrayList<>();
    }

    /**
     * Return the name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Return the password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Return the list of friends
     * @return List(User)
     */
    public List<User> getFriends() {
        return friends;
    }

    /**
     * Set the name of the user
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the password of the user
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Add a friend to the list of friends
     * @param friend User
     */
    public void addFriend(User friend){
        if(!friends.contains(friend))
            friends.add(friend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(friends, user.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id= " + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
