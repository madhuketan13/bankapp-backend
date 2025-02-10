package com.dnb.accountservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dnb.accountservice.dto.Account;
import com.dnb.accountservice.dto.Loan;
import com.dnb.accountservice.dto.Transaction;
import com.dnb.accountservice.dto.User;
import com.dnb.accountservice.exceptions.IdNotFoundException;
import com.dnb.accountservice.exceptions.InsufficientBalanceException;
import com.dnb.accountservice.repo.AccountRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${api.user1}")
	private String URL;
	
	@Value("${api.loan}")
	private String loanURL;

	@Override
	public Account createAccount(Account account) throws IdNotFoundException {
		
		try {
			System.out.println(account.getUserId());
			ResponseEntity<User> responseEntity = restTemplate.getForEntity(URL+"/"+account.getUserId() , User.class);
			return accountRepository.save(account);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
//			throw new IdNotFoundException(e.getMessage());
		}
		return null;
	}
	
	@Override
    public Double withdraw(Transaction transaction) throws InsufficientBalanceException {
        Account account = accountRepository.findById(transaction.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        Double currentBalance = account.getAccountBalance();
        Double withdrawalAmount = transaction.getAmount();

        if ( (currentBalance - withdrawalAmount) < 10000) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        Double newBalance = currentBalance - withdrawalAmount;
        account.setAccountBalance(newBalance);
        accountRepository.save(account);
        
		return newBalance;
    }
	
	@Override
    public Double deposit(Transaction transcation) {
		
		System.out.println(accountRepository.findById(transcation.getAccountId()));
        Account account = accountRepository.findById(transcation.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        Double currentBalance = account.getAccountBalance();
        Double newBalance = currentBalance + transcation.getAmount();
        account.setAccountBalance(newBalance);
        accountRepository.save(account);

        return newBalance;
    }

	@Override
	public Optional<Account> getAccountDetails(String accountId) {
		// TODO Auto-generated method stub
		return accountRepository.findById(accountId);
	}

	
	@Override
	public List<Loan> getLoanByaccountId(String accountId) {

		List<Loan> loan =  restTemplate.getForEntity(loanURL + "/getLoan/" + accountId, List.class).getBody();
		return loan;
	}

	@Override
	public Optional<Account> getAccountByUserId(int userId) {
		// TODO Auto-generated method stub
		return accountRepository.findByUserId(userId);
	}
	
	
	

}
