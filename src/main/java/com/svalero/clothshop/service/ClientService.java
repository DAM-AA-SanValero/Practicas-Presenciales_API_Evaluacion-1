package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.exception.ClientNotFoundException;

import java.util.List;

public interface ClientService {

    List<Client> findAll();
    Client findById(long id) throws ClientNotFoundException;

    Client addClient(Client client);

    void deleteClient(long id) throws ClientNotFoundException;

    Client updateClient(long id, Client updateClient) throws ClientNotFoundException;
    Client updateClientAddress(long id, String newName) throws ClientNotFoundException;

    List<Client> findByName(String name) throws ClientNotFoundException;


}
