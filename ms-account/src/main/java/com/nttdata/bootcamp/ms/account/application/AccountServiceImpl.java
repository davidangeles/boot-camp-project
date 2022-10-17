package com.nttdata.bootcamp.ms.account.application;
 
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.bootcamp.ms.commons.base.domain.AccountDTO; 
 
import reactor.core.publisher.Mono; 

public class AccountServiceImpl implements AccountService {
	
	  WebClient client = WebClient.create("http://localhost:8083");
	 
	@Override
	public Mono<AccountDTO> create(AccountDTO accountDTO) { 
		  return client.post()
	                .uri("/account/create")
	                .body(Mono.just(accountDTO), AccountDTO.class)
	                .retrieve()
	                .bodyToMono(AccountDTO.class);
	}

	@Override
	public Mono<ServerResponse> cancel(Integer id) { 
        return client.get()
                .uri("/account/cancel/{id}", id)
                .retrieve()
                .bodyToMono(ServerResponse.class);
	}	
	
	@Override
	public Mono<ServerResponse> associate(Integer id, Integer group) { 
		   return client.get()
	                .uri("/account/associate/{id}/{group}", id, group)
	                .retrieve()
	                .bodyToMono(ServerResponse.class);
	} 
}
