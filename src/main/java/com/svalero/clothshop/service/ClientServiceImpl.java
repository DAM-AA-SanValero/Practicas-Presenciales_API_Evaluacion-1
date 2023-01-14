package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.exception.ClientNotFoundException;
import com.svalero.clothshop.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(long id) throws ClientNotFoundException {
        return clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(long id) throws ClientNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        clientRepository.delete(client);
    }

    @Override
    public Client updateClient(long id, Client newClient) throws ClientNotFoundException{
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        client.setName(newClient.getName());
        client.setAddress(newClient.getAddress());
        client.setAccount(newClient.getAccount());
        return clientRepository.save(client);
    }

    @Override
    public Client updateClientAddress(long id, String newAddress) throws ClientNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        client.setAddress(newAddress);
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findByName(String name) throws ClientNotFoundException{
        return clientRepository.findByName(name);
    }
}
