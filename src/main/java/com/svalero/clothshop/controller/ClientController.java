package com.svalero.clothshop.controller;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients(@RequestParam(name = "name", defaultValue = "") String name) {
        if (name.equals("")) {
            return ResponseEntity.status(200).body(clientService.findAll());
        }
        return ResponseEntity.status(200).body(clientService.findByName(name));
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientId(@PathVariable long id){
        Client client = clientService.findById(id);
        return ResponseEntity.status(200).body(client);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client newClient = clientService.addClient(client);
        return ResponseEntity.status(201).body(newClient);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody Client client){
        Client newClient = clientService.updateClient(id,client);
        return ResponseEntity.status(200).body(newClient);
    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<Client> updateClientAddress(@PathVariable long id, @RequestBody Client client){
        Client updateAddress = clientService.updateClientAddress(id, client.getAddress());
        return ResponseEntity.status(200).body(updateAddress);
    }
}
