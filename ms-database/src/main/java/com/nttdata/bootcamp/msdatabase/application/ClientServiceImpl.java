package com.nttdata.bootcamp.msdatabase.application;

import com.nttdata.bootcamp.msdatabase.infraestructure.ClientRepository;
import com.nttdata.bootcamp.msdatabase.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Mono<Client> create(Mono<Client> clientMono) {
        return clientMono.flatMap(clientRepository::insert);
    }

    @Override
    public Flux<Client> listAll() {
        return clientRepository.findAll();
    }
}
