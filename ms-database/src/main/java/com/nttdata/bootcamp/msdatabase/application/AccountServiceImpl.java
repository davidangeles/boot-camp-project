package com.nttdata.bootcamp.msdatabase.application;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.bootcamp.ms.commons.base.domain.AccountDTO; 
import com.nttdata.bootcamp.msdatabase.infraestructure.AccountRepository;
import com.nttdata.bootcamp.msdatabase.model.Account;
 
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	 private static Mono<ServerResponse> notFound = ServerResponse.notFound().build();
	
	@Override
	public Mono<AccountDTO> create(AccountDTO accountDTO) { 
		Account account = new Account();
		account.setId(accountDTO.getId());
		account.setCommission(accountDTO.getCommission());
		account.setMovementCount(accountDTO.getMovementCount());
		account.setType(accountDTO.getType());
		account.setStatus(accountDTO.getStatus());
		 
		if(accountDTO.getAccountGroup() != null) { 
			account.setAccountGroup(Integer.parseInt(accountDTO.getAccountGroup().getId()));
		} 
		
		return Mono.just(account).flatMap(accountRepository::insert).map(p-> toDto(p));
	}

	@Override
	public Mono<ServerResponse> cancel(Integer id) { 
		Mono<Account> account = accountRepository.findById(String.valueOf(id));
		
		account.flatMap(acc -> {
				 acc.setStatus(false);
				 return accountRepository.save(acc);
			});
		
		return  account.flatMap(p -> 
				ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
					.body(fromObject(p))
					).switchIfEmpty(notFound);
	}	
	
	@Override
	public Mono<ServerResponse> associate(Integer id, Integer group) { 
		Mono<Account> account = accountRepository.findById(String.valueOf(id));
		
		account.flatMap(acc -> { 
			acc.setAccountGroup(group);
			return accountRepository.save(acc);
		});
		 
		return  account.flatMap(p -> 
				ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
					.body(fromObject(p))
					).switchIfEmpty(notFound);
	}

	
	private AccountDTO toDto(Account account) { 
		return AccountDTO.builder() 
				.id(account.getId())
				.commission(account.getCommission())
				.movementCount(account.getMovementCount())
				.type(account.getType())
				.status(account.getStatus())
				.build();
	}



}
