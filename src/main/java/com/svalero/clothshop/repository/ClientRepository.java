package com.svalero.clothshop.repository;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.exception.ClientNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();

    List<Client> findByName(String name) throws ClientNotFoundException;

}
