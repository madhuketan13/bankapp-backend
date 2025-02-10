package com.dnb.loanservice.dto;

import com.dnb.loanservice.utils.AccountType;

import lombok.Data;


@Data
public class Account {
	
	private String accountId;
	private AccountType accountType;
	private Double accountBalance;
	private String pancardNumber;
	private String aadharcardNumber;
	private String accountStatus;
	private String createDateTime;
	private int userId;

}
