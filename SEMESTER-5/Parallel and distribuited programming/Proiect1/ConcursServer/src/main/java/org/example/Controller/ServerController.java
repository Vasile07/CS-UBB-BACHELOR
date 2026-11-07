package org.example.Controller;

import org.example.Business.ClasamentService;
import org.example.Business.DataService;
import org.example.Model.ClasamentDTO;
import org.example.Model.ClasamentFinalDTO;
import org.example.Model.DataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/concurs")
public class ServerController {

    @Autowired
    private ClasamentService clasamentService;

    @PostMapping("/sendData")
    public ResponseEntity<String> receiveData(@RequestBody List<DataDTO> data) {
        System.out.println(data);
        clasamentService.addDataToQueue(data);
        return ResponseEntity.ok("Datele au fost primite.");
    }

    @GetMapping("/getPartialClasament/{tara}")
    public CompletableFuture<List<ClasamentDTO>> getPartialClasament(@PathVariable String tara) {
        System.out.println("S-a primit cererea de clasament partial de la tara %s".formatted(tara));
        return clasamentService.getPartialClasamentAsync(tara);
    }

    @GetMapping("/getClasamentFinal")
    public ClasamentFinalDTO getClasamentFinal() throws InterruptedException {
        System.out.println("S-a primit o cerere de clasament final");
        return clasamentService.getClasamentFinal();
    }
}
