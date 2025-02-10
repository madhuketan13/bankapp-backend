package com.dnb.accountservice.dto;

import lombok.Data;

@Data
public class Transaction {
	
	private String accountId;
    private Double amount;

}
