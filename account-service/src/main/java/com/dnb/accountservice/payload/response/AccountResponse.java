package com.dnb.accountservice.payload.response;

import java.util.List;

import com.dnb.accountservice.dto.Loan;
import com.dnb.accountservice.enums.AccountType;

import lombok.Data;

@Data
public class AccountResponse {
	
	private String accountStatus;
	private AccountType accountType;
	private Double accountBalance;
	private String pancardNumber;
	private String aadharcardNumber;
	private String createDateTime;
	private int userId;
	private String accountId;
	
	private List<Loan> loans;

}
