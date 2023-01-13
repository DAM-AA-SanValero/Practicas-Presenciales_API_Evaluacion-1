package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
    }

    @Override
    public Client updateClient(long id, Client newClient) {
        Client client = clientRepository.findById(id).orElseThrow();
        client.setName(newClient.getName());
        client.setAddress(newClient.getAddress());
        client.setAccount(newClient.getAccount());
        return clientRepository.save(client);
    }

    @Override
    public Client updateClientAddress(long id, String newAddress) {
        Client client = clientRepository.findById(id).orElseThrow();
        client.setAddress(newAddress);
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }
}
