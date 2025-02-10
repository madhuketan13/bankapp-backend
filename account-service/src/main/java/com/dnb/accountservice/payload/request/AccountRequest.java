package com.dnb.accountservice.payload.request;

import com.dnb.accountservice.enums.AccountType;

import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class AccountRequest {
	
	private String accountStatus;
	private AccountType accountType;
	private Double accountBalance;
	private String pancardNumber;
	private String aadharcardNumber;
	private String createDateTime;
	private int userId;

}
