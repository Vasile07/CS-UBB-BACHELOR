package Test.Persistance;

import Domain.User;
import Exceptions.ExistingEntityException;
import Exceptions.InexistingEntityException;
import Validator.AbstractValidator;
import Validator.UserValidator;
import Persistance.InMemoryRepository;

import java.util.ArrayList;
import java.util.Objects;

public class InMemoryRepoTest implements PersistanceTest {
    private final User u1 = new User("A","AA");
    private final User u2 = new User("B","BB");
    private final User u3 = new User("C","CC");
    private final User u4 = new User("D","DD");
    InMemoryRepository<Long,User> userRepository;
    public InMemoryRepoTest() {
        userRepository = new InMemoryRepository<>(new UserValidator());
        u1.setId(1L);
        u2.setId(2L);
        u3.setId(3L);
        u4.setId(4L);
    }

    @Override
    public void addTest() {
        assert userRepository.numberOfEntities() == 0;
        userRepository.add(u1);
        userRepository.add(u2);
        userRepository.add(u3);
        userRepository.add(u4);
        assert userRepository.numberOfEntities() == 4;
        try{
            userRepository.add(u1);
            assert false;
        }catch (ExistingEntityException err) {
            assert true;
        }
        try{
            userRepository.add(null);
            assert false;
        }catch (IllegalArgumentException err){
            assert true;
        }
    }

    @Override
    public void deleteTest() {
        userRepository.deleteByID(u1.getId());
        userRepository.deleteByID(u2.getId());
        userRepository.deleteByID(u3.getId());
        userRepository.deleteByID(u4.getId());
        assert userRepository.numberOfEntities() == 0;
        try{
            userRepository.deleteByID(null);
            assert false;
        }catch (IllegalArgumentException err){
            assert true;
        }
        try{
            userRepository.deleteByID(u1.getId());
            assert false;
        }catch (InexistingEntityException err){
            assert true;
        }

    }

    @Override
    public void getAllTest() {
        assert userRepository.numberOfEntities() == 4;
    }

    @Override
    public void foundTest() {
        assert Objects.equals(userRepository.findByID(u1.getId()), u1);
        try{
            userRepository.findByID(null);
            assert false;
        }catch (IllegalArgumentException err){
            assert true;
        }
        try{
            userRepository.findByID(5L);
            assert false;
        }catch (InexistingEntityException err){
            assert true;
        }

    }

    @Override
    public void updateTest() {
        User newU1 = new User(u1.getName() + " Paul",u1.getPassword() + " adca");
        newU1.setId(u1.getId());
        userRepository.update(newU1);
        assert Objects.equals(userRepository.findByID(newU1.getId()), newU1);
        try{
            userRepository.update(null);
            assert false;
        }catch (IllegalArgumentException err){
            assert true;
        }
        try{
            User inexistingUser = new User("dkmakca","ckanckaca");
            inexistingUser.setId(3010L);
            userRepository.update(inexistingUser);
            assert false;
        }catch (InexistingEntityException err){
            assert true;
        }
    }
}
