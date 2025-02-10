package com.dnb.accountservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.accountservice.dto.Account;
import com.dnb.accountservice.dto.Loan;
import com.dnb.accountservice.dto.Transaction;
import com.dnb.accountservice.exceptions.IdNotFoundException;
import com.dnb.accountservice.exceptions.InsufficientBalanceException;
import com.dnb.accountservice.payload.request.AccountRequest;
import com.dnb.accountservice.payload.response.AccountResponse;
import com.dnb.accountservice.service.AccountService;
import com.dnb.accountservice.utils.AccountRequestToEntity;
import com.dnb.accountservice.utils.EntityToProfileResponse;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	AccountRequestToEntity mapper;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	EntityToProfileResponse entityToResponseMapper;
	
	@PostMapping("/create")
	public ResponseEntity<?> createAccount(@RequestBody AccountRequest account) throws IdNotFoundException {
		Account accountRequest = mapper.getAccountEntity(account);
		Account accountData = accountService.createAccount(accountRequest);
		return new ResponseEntity(accountData, HttpStatus.CREATED);

	}
	
	@GetMapping("/{accountId}")
	public ResponseEntity<Account> getAccountDetails(@PathVariable("accountId") String accountId) throws IdNotFoundException {

		
		Optional<Account> optional = accountService.getAccountDetails(accountId);
		if (optional.isPresent()) {

			return ResponseEntity.ok(optional.get());
		} else {

			throw new IdNotFoundException("ID not found");
		}

	}
	
	@GetMapping("/getAccount/{userId}")
	public ResponseEntity<?> getAccountByUserId(@PathVariable("userId") int userId) throws IdNotFoundException{
		
		
		Optional<Account> optional = accountService.getAccountByUserId(userId);
		if (optional.isPresent()) {
			List<Loan> loan = accountService.getLoanByaccountId(optional.get().getAccountId());
			
			AccountResponse accountResponse = entityToResponseMapper.accountEntityToResponse(optional.get(), loan);

			return ResponseEntity.ok(accountResponse);
		} else {

			throw new IdNotFoundException("userID not found");
		}
		
	}
	
	@PostMapping("/withdraw")
    public ResponseEntity<Double> withdraw(@RequestBody Transaction transaction) throws InsufficientBalanceException {
        Double newBalance = accountService.withdraw(transaction);
        return ResponseEntity.ok(newBalance);
    }
	
	@PostMapping("/deposit")
    public ResponseEntity<Double> deposit(@RequestBody Transaction transaction) {
        Double newBalance = accountService.deposit(transaction);
        return ResponseEntity.ok(newBalance);
    }
	

}
