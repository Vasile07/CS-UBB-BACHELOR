package org.example.Utils;

import org.example.Model.ClasamentDTO;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileWriter {

    // Method to write to a CSV file
    public static void writeToFile(List<String> headers, List<ClasamentDTO> clasament, String tara) {
        // Create directory if it doesn't exist
        Path directoryPath = Paths.get("Clasament", tara);
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        // Path to the CSV file
        Path filePath = Paths.get(directoryPath.toString(), "clasamentPartial.csv");

        // Try to write to the file
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {

            // Write headers (comma-separated)
            writer.write(String.join(",", headers));
            writer.newLine();

            // Write each ClasamentDTO to the file
            for (ClasamentDTO clasamentDTO : clasament) {
                writer.write("%s,%d".formatted(clasamentDTO.getId(), clasamentDTO.getPunctajFinal()));  // You need to implement `toCsvString` in ClasamentDTO
                writer.newLine();
            }

            System.out.println("File written successfully: " + filePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

