package com.dnb.loanservice.utils;

import com.dnb.loanservice.enums.LoanType;
import org.springframework.stereotype.Component;

import com.dnb.loanservice.dto.Loan;
import com.dnb.loanservice.payload.request.LoanRequest;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LoanRequestToEntity {
	
	public Loan getLoanEntity(LoanRequest loanRequest) {
		Loan loan = new Loan();
		Set<LoanType> loanTypes = loanRequest.getLoanType().stream()
				.map(LoanType::valueOf)
				.collect(Collectors.toSet());
		loan.setLoanType(loanTypes);
		loan.setLoanAmount(loanRequest.getLoanAmount());
		loan.setAppliedDate(loanRequest.getAppliedDate());
		loan.setAccountId(loanRequest.getAccountId());
		
		return loan;
	}

}
