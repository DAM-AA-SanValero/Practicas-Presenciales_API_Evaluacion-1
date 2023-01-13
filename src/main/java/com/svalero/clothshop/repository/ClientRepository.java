package com.svalero.clothshop.repository;

import com.svalero.clothshop.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findall();

    List<Client> findByName(String name);

}
