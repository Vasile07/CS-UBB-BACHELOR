package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Model.ClasamentDTO;
import org.example.Model.ClasamentFinalDTO;
import org.example.Model.DataDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {
    private final String tara;

    private final Integer deltaX;

    public Client(String tara, Integer deltaX) {
        this.tara = tara;
        this.deltaX = deltaX;
    }

    public void start() {
        String filePath = "Results/" + tara + ".csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<DataDTO> datas = new ArrayList<>();
            String line;
            line = reader.readLine(); // read header

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                DataDTO data = new DataDTO(tara, Integer.parseInt(columns[0]), Integer.parseInt(columns[1])); // Adjust this to your data structure
                datas.add(data);

                if (datas.size() == 20) {
                    sendDatas(datas);
                    datas.clear();
                    Thread.sleep(deltaX * 1000);
                }
            }

            if (!datas.isEmpty()) {
                sendDatas(datas);
            }

            System.out.println("Toate datele au fost trimise de la tara %s".formatted(tara));

            getPartialClasament();

            getFinalClasament();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void sendDatas(List<DataDTO> datas) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(datas);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/concurs/sendData"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("[%s] Response: %s".formatted(tara, response.body()));
    }

    private void getPartialClasament() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/concurs/getPartialClasament/" + tara))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String jsonResponse = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            List<ClasamentDTO> clasamentList = objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, ClasamentDTO.class));

            org.example.Utils.FileWriter.writeToFile(Arrays.asList("Tara","Punctaj total"), clasamentList, tara);
        } else {
            System.out.println("Request failed with status: " + response.statusCode());
        }
    }

    private void getFinalClasament() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/concurs/getClasamentFinal"))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String jsonResponse = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            ClasamentFinalDTO clasamentFinal = objectMapper.readValue(jsonResponse, ClasamentFinalDTO.class);

            System.out.println("Clasament final");
            System.out.println(clasamentFinal);
        } else {
            System.out.println("Request failed with status: " + response.statusCode());
        }
    }
}
