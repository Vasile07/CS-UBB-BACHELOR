package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutomatFinit {
    private List<String> stari;
    private List<String> alfabet;
    private String stareStart;
    private List<String> stariFinale;
    private List<String>[][] tranzitii;

    public AutomatFinit(String fisier){
        try {
            File myObj = new File(fisier);
            Scanner myReader = new Scanner(myObj);
            String linieStari = myReader.nextLine();
            String linieAlfabet = myReader.nextLine();
            String linieStareStart = myReader.nextLine();
            String linieStariFinale = myReader.nextLine();

            stari = List.of(linieStari.split(","));
            alfabet = List.of(linieAlfabet.split(","));
            stareStart = linieStareStart;
            stariFinale = List.of(linieStariFinale.split(","));

            tranzitii = new ArrayList[stari.size()][stari.size()]; // Use raw type here

            for (int i = 0; i < stari.size(); i++) {
                for (int j = 0; j < stari.size(); j++) {
                    tranzitii[i][j] = new ArrayList<>(); // Now we can specify <String>
                }
            }

            while (myReader.hasNextLine()) {
                String tranzitie = myReader.nextLine();
                String[] elemente = tranzitie.split(",");
                int sursa = stari.indexOf(elemente[0]);
                int destinatie = stari.indexOf(elemente[2]);
                String simbol = elemente[1];
                tranzitii[sursa][destinatie].add(simbol);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void afiseazaAutomatFinit(){
        System.out.println("Stari:");
        stari.forEach(stare -> System.out.print(stare + " "));
        System.out.println();
        System.out.println();

        System.out.println("Alfabetul:");
        alfabet.forEach(simbol -> System.out.print(simbol + " "));
        System.out.println();
        System.out.println();

        System.out.println("Tranzitii:");
        for(int i =0 ; i < stari.size() ; i++)
            for(int j = 0 ; j < stari.size() ; j ++)
                for(String simbol : tranzitii[i][j])
                    System.out.println(stari.get(i) + "---" + simbol + "-->" + stari.get(j));
        System.out.println();

        System.out.println("Stari finale:");
        stariFinale.forEach(stareFin -> System.out.print(stareFin + " "));
        System.out.println();
    }

    public boolean eAcceptat(String secventa){
        return dfs(stareStart, secventa);
    }

    private boolean dfs(String stare, String secventa){
        if(secventa.isEmpty())
            if(stariFinale.contains(stare))
                return true;
            else
                return false;
        int indexStare = stari.indexOf(stare);
        for(int j = 0 ; j < stari.size() ; j ++)
            for(String simbol : tranzitii[indexStare][j])
                if(simbol.charAt(0) == secventa.charAt(0))
                    return dfs(stari.get(j),secventa.substring(1));
        return false;
    }

    public String celMaiLungPrefix(String secventa){
        for(int i = secventa.length() -1 ; i >= 0 ; i --)
            if(dfs(stareStart, secventa.substring(0,i)))
                return secventa.substring(0,i);
        return "";
    }
}
