package com.dnb.accountservice.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.accountservice.dto.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
	
	Optional<Account> findByUserId(int UserId);

}
