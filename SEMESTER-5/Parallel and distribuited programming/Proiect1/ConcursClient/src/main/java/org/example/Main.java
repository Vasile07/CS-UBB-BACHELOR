package org.example;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Client clientRomania = new Client("Romania", 10);
        Client clientAustria = new Client("Austria", 15);



        // Create and start a thread for Austria
        Thread threadAustria = new Thread(clientAustria::start);
        threadAustria.start();

        Thread.sleep(2000);
        // Create and start a thread for Romania
        Thread threadRomania = new Thread(clientRomania::start);
        threadRomania.start();
    }
}