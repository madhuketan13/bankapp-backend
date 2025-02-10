package com.dnb.accountservice.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dnb.accountservice.dto.Account;
import com.dnb.accountservice.dto.Loan;
import com.dnb.accountservice.payload.response.AccountResponse;

@Component
public class EntityToProfileResponse {
	
	public AccountResponse helper(Account account) {
		AccountResponse accountResponse = new AccountResponse();
		
		
		
		accountResponse.setAccountStatus(account.getAccountStatus());
		accountResponse.setAccountType(account.getAccountType());
		accountResponse.setAccountBalance(account.getAccountBalance());
		accountResponse.setAadharcardNumber(account.getAadharcardNumber());
		accountResponse.setPancardNumber(account.getPancardNumber());
		accountResponse.setCreateDateTime(account.getCreateDateTime());
		accountResponse.setUserId(account.getUserId());

 

		return accountResponse;
	}
	
	public AccountResponse accountEntityToResponse(Account account , List<Loan> loans) {
		
		AccountResponse accountResponse = this.helper(account);
		accountResponse.setLoans(loans);
		
		return accountResponse;
	}
}
