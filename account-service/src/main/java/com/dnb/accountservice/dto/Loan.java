package com.dnb.accountservice.dto;

import com.dnb.accountservice.utils.LoanStatus;
import com.dnb.accountservice.utils.LoanType;

import lombok.Data;

@Data
public class Loan {
	
	private String loanId;
	private LoanType loanType;
	private int loanAmount;
	private LoanStatus loanStatus = LoanStatus.PENDING;
	private String appliedDate;
	private String accountId;

}
