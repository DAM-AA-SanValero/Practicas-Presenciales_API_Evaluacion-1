package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll();
    Client findById(long id);

    Client addClient(Client client);

    void deleteClient(long id);

    Client updateClient(long id, Client updateClient);
    Client updateClientAddress(long id, String newName);

    List<Client> findByName(String name);


}
