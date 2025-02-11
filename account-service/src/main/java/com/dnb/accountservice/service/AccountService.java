package com.dnb.accountservice.service;

import java.util.List;
import java.util.Optional;

import com.dnb.accountservice.dto.Account;
import com.dnb.accountservice.dto.Loan;
import com.dnb.accountservice.dto.Transaction;
import com.dnb.accountservice.exceptions.IdNotFoundException;
import com.dnb.accountservice.exceptions.InsufficientBalanceException;

public interface AccountService {
	
	public Account createAccount(Account account) throws IdNotFoundException;
	
	public Optional<Account> getAccountDetails(String accountId);

	public Double withdraw(Transaction transaction) throws InsufficientBalanceException;

	Double deposit(Transaction transcation);

	List<Loan> getLoanByaccountId(String accountId);
	
	public Optional<Account> getAccountByUserId(int userId);

	public Account getAccountById(String accountId);

	public Account updateAccount(Account account);

}
