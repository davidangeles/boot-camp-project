package com.nttdata.bootcamp.msdatabase.application;

import com.nttdata.bootcamp.msdatabase.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Mono<Client> create(Mono<Client> client);
    Flux<Client> listAll();

}
