package com.nttdata.bootcamp.msdatabase.application;

import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.bootcamp.ms.commons.base.domain.AccountDTO;  
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<AccountDTO> create(AccountDTO accountDTO);

    Mono<ServerResponse> associate(Integer id, Integer group);
    
    Mono<ServerResponse> cancel(Integer id);
    
    

}
