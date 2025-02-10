package com.dnb.loanservice.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.loanservice.dto.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan, String> {
	
	List<Loan> findByAccountId(String accountId);

}
