package com.svalero.clothshop.controller;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.exception.ClientNotFoundException;
import com.svalero.clothshop.exception.ErrorMessage;
import com.svalero.clothshop.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients(@RequestParam(name = "name", defaultValue = "")
                                                       String name) throws ClientNotFoundException {
        if (name.equals("")) {
            logger.info("Lista de clientes mostrada");
            return ResponseEntity.status(200).body(clientService.findAll());
        }
        logger.info("Filtrado por nombre del cliente");
        return ResponseEntity.status(200).body(clientService.findByName(name));
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientId(@PathVariable long id) throws ClientNotFoundException{
        Client client = clientService.findById(id);
        logger.info("Búsqueda por id de cliente");
        return ResponseEntity.status(200).body(client);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client)  {
        Client newClient = clientService.addClient(client);
        logger.info("Cliente añadido");
        return ResponseEntity.status(201).body(newClient);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id) throws ClientNotFoundException{
        clientService.deleteClient(id);
        logger.info("Cliente eliminado");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id,
                                               @RequestBody Client client) throws ClientNotFoundException{
        Client newClient = clientService.updateClient(id,client);
        logger.info("Cliente actualizado");
        return ResponseEntity.status(200).body(newClient);
    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<Client> updateClientAddress(@PathVariable long id,
                                                      @RequestBody Client client) throws ClientNotFoundException{
        Client updateAddress = clientService.updateClientAddress(id, client.getAddress());
        logger.info("Actualizada la dirección del cliente");
        return ResponseEntity.status(200).body(updateAddress);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessage> clientNotFoundException(ClientNotFoundException cnfe){
        logger.error(cnfe.getMessage(),cnfe);
        ErrorMessage notfound = new ErrorMessage(404,cnfe.getMessage());
        return new ResponseEntity(notfound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> badRequestException(MethodArgumentNotValidException manve){

        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldname = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldname, message);
        });

        logger.error(manve.getMessage(),manve);
        ErrorMessage badRequest = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error(e.getMessage(),e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
