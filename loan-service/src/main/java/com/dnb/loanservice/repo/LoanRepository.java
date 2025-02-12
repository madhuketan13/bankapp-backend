package com.dnb.loanservice.repo;

import java.util.List;

import com.dnb.loanservice.enums.LoanStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.loanservice.dto.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan, String> {
	
	List<Loan> findByAccountId(String accountId);

	List<Loan> findByLoanStatus(LoanStatus status);

}
