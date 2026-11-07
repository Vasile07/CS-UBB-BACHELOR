package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileStore {
    public static void writeTSToFile(ListaOrdonata<String> TS){
        String fileName = "TS.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Position,Atom");
            writer.newLine();
            for (int i = 0 ; i < TS.size() ; i ++) {
                writer.write((i + 1) + "," + TS.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFIPToFile(List<FIPEntity> FIP){
        String fileName = "FIP.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Cod,Pozitie in TS");
            writer.newLine();
            for (FIPEntity FIPEntity : FIP) {
                writer.write(FIPEntity.getCod() + "," + FIPEntity.getPozInTS());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
