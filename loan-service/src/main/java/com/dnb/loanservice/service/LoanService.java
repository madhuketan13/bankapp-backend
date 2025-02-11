package com.dnb.loanservice.service;

import java.util.List;
import java.util.Optional;

import com.dnb.loanservice.dto.Loan;
import com.dnb.loanservice.exceptions.IdNotFoundException;

public interface LoanService {
	
	public Loan createLoan(Loan loan) throws IdNotFoundException;
	
	public Optional<Loan> getLoanDetails(String loanId);	
	
	public List<Loan> getLoanDetailsByAccountId(String accountId);

	public List<Loan> getAllLoans();
}
