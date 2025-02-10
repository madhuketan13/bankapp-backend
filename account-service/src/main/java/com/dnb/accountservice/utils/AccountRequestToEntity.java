package com.dnb.accountservice.utils;

import org.springframework.stereotype.Component;

import com.dnb.accountservice.dto.Account;
import com.dnb.accountservice.payload.request.AccountRequest;

@Component
public class AccountRequestToEntity {
	
	public Account getAccountEntity(AccountRequest accountRequest) {
		Account account = new Account();
		account.setAccountType(accountRequest.getAccountType());
		account.setAccountBalance(accountRequest.getAccountBalance());
		account.setCreateDateTime(accountRequest.getCreateDateTime());
		account.setAadharcardNumber(accountRequest.getAadharcardNumber());
		account.setPancardNumber(accountRequest.getPancardNumber());
		account.setAccountStatus(accountRequest.getAccountStatus());
		account.setUserId(accountRequest.getUserId());
		return account;
	}
	
	
	

}
