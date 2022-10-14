package com.nttdata.bootcamp.msdatabase.application;

import com.nttdata.bootcamp.msdatabase.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Client> create(@RequestBody Client client) {
        return clientService.create(Mono.just(client));
    }

    @GetMapping(value = "listAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Client> listAll() {
        return clientService.listAll();
    }


}
