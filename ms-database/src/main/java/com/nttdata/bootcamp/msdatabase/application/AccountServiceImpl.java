package com.nttdata.bootcamp.msdatabase.application;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.bootcamp.ms.commons.base.domain.AccountDTO;
import com.nttdata.bootcamp.ms.commons.base.domain.AccountGroup;
import com.nttdata.bootcamp.msdatabase.infraestructure.AccountRepository;
import com.nttdata.bootcamp.msdatabase.model.Account;
 
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Service
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
		account.setAccountGroup(accountDTO.getAccountGroup());
		account.setType(accountDTO.getType());
		account.setStatus(accountDTO.getStatus());
		  
		return Mono.just(account).flatMap(accountRepository::insert).map(p-> toDto(p));
	}

	@Override
	public Mono<AccountDTO> cancel(String id) { 
		Mono<Account> account = accountRepository.findById(String.valueOf(id));
		 
		return account.flatMap(acc -> { 
			acc.setStatus(false);
			return Mono.just(acc).flatMap(accountRepository::save).map(p-> toDto(p));
		});
	}	
	
	@Override
	public Mono<AccountDTO> associate(String id, Integer group) { 
		Mono<Account> account = accountRepository.findById(String.valueOf(id));
		
		return account.flatMap(acc -> { 
			acc.setAccountGroup(group);
			return Mono.just(acc).flatMap(accountRepository::save).map(p-> toDto(p));
		});
	}

	
	private AccountDTO toDto(Account account) {  
		return AccountDTO.builder() 
				.id(account.getId())
				.commission(account.getCommission())
				.movementCount(account.getMovementCount())
				.accountGroup(account.getAccountGroup())
				.type(account.getType()) 
				.status(account.getStatus())
				.build();
	}
 
}
