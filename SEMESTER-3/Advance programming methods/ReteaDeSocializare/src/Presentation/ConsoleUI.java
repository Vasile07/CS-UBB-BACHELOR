package Presentation;

import Domain.Friendship;
import Domain.User;
import Exceptions.ExistingEntityException;
import Exceptions.InexistingEntityException;
import Exceptions.ValidationException;
import Service.ServiceFriendship;
import Service.ServiceUsers;

import java.util.Scanner;

public class ConsoleUI implements UI{

    ServiceUsers serviceUsers;
    ServiceFriendship serviceFriendship;

    public ConsoleUI(ServiceUsers serviceUsers, ServiceFriendship serviceFriendship) {
        this.serviceUsers = serviceUsers;
        this.serviceFriendship = serviceFriendship;
    }

    @Override
    public void runUI() {
        Scanner scanner =  new Scanner(System.in);
        seeMenuUI();
        while(true){
            System.out.println("Give option");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    addUserUI();
                    break;
                case 2:
                    removeUserUI();
                    break;
                case 3:
                    addFriendshipUI();
                    break;
                case 4:
                    removeFriendshipUI();
                    break;
                case 5:
                    numberOfComunitties();
                    break;
                case 6:
                    mostSociableComunittyUI();
                    break;
                case 7:
                    seeUsers();
                    break;
                case 8:
                    seeFriendships();
                    break;
                case 9:
                    return;
                case 0:
                    seeMenuUI();
                    break;
                default:
                    System.out.println("Optiune invalida!");
            }
            System.out.println();
        }
    }
    private void seeMenuUI(){
        System.out.println("MENU");
        System.out.println("1 - Add user");
        System.out.println("2 - Remove user");
        System.out.println("3 - Add Friendship");
        System.out.println("4 - Remove Friendship");
        System.out.println("5 - See the number of comunitties");
        System.out.println("6 - See the members of the most sociable comunitty");
        System.out.println("7 - See Users");
        System.out.println("8 - See Friendships");
        System.out.println("9 - Exit");
        System.out.println("0 - see Menu");
    }
    private void addUserUI(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give name:");
        String name = scanner.nextLine();
        System.out.println("Give password:");
        String password = scanner.nextLine();
        try{
            serviceUsers.addUser(name,password);
            System.out.println("User added successfully!");
        }catch (IllegalArgumentException | ExistingEntityException | ValidationException e){
            System.out.println(e.getMessage());
        }
    }
    private void removeUserUI(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give id:");
        Long id = scanner.nextLong();
        try{
            serviceUsers.removeUserById(id);
            System.out.println("User removed successfully!");
        }catch (IllegalArgumentException | InexistingEntityException | ValidationException e){
            System.out.println(e.getMessage());
        }
    }
    private void addFriendshipUI(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give first id:");
        Long id1 = scanner.nextLong();
        System.out.println("Give second id:");
        Long id2 = scanner.nextLong();
        try{
            serviceFriendship.addFriendship(id1,id2);
            System.out.println("Friendship added successfully!");
        }catch (IllegalArgumentException | ExistingEntityException | InexistingEntityException | ValidationException e){
            System.out.println(e.getMessage());
        }
    }
    private void removeFriendshipUI(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give first id:");
        Long id1 = scanner.nextLong();
        System.out.println("Give second id:");
        Long id2 = scanner.nextLong();
        try{
            serviceFriendship.removeFriendshipById(id1,id2);
            System.out.println("Friendship removed successfully!");
        }catch (IllegalArgumentException | InexistingEntityException | ValidationException e){
            System.out.println(e.getMessage());
        }
    }
    private void numberOfComunitties(){
        System.out.println("Number of communities:" + serviceFriendship.numberOfCommuitties());
    }
    private void mostSociableComunittyUI(){
        System.out.println("Members:");
        for(User user : serviceFriendship.mostSociableComunitty())
            System.out.println(user);
    }
    private void seeUsers(){
        for(User user : serviceUsers.getAllUsers())
            System.out.println(user);
    }
    private void seeFriendships(){
        for(Friendship friendship : serviceFriendship.getAllFriendships())
            System.out.println(friendship);
    }
}
