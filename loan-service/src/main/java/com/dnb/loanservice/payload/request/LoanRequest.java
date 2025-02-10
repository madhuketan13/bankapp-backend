package com.dnb.loanservice.payload.request;

import com.dnb.loanservice.dto.Loan;
import com.dnb.loanservice.enums.LoanStatus;
import com.dnb.loanservice.enums.LoanType;

import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Set;

@Data
public class LoanRequest {
	
	private Set<String> loanType;
	
	private int loanAmount;
	
	private String appliedDate;
	
	private String accountId;

}
