package com.nttdata.bootcamp.ms.client.application;

import com.nttdata.bootcamp.ms.commons.base.domain.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    WebClient client = WebClient.create("http://localhost:8082");

    @Override
    public Mono<ClientDTO> create(ClientDTO clientDTO) {
        return client.post()
                .uri("/client/create")
                .body(Mono.just(clientDTO), ClientDTO.class)
                .retrieve()
                .bodyToMono(ClientDTO.class);
    }

    @Override
    public Mono<ClientDTO> findById(String code) {
        return client.get()
                .uri("/client/findById/{id}", code)
                .retrieve()
                .bodyToMono(ClientDTO.class);
    }

    @Override
    public Flux<ClientDTO> listAll() {
        return client.get()
                .uri("/client/listAll")
                .retrieve()
                .bodyToFlux(ClientDTO.class);
    }

}
