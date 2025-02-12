package com.dnb.loanservice.service;

import java.util.List;
import java.util.Optional;

import com.dnb.loanservice.enums.LoanStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dnb.loanservice.dto.Account;
import com.dnb.loanservice.dto.Loan;
import com.dnb.loanservice.exceptions.IdNotFoundException;
import com.dnb.loanservice.repo.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${api.account}")
	private String URL;

	@Override
	public Loan createLoan(Loan loan) throws IdNotFoundException {
		try {
			ResponseEntity<Account> responseEntity = restTemplate.getForEntity(URL+"/"+loan.getAccountId() , Account.class);
			return loanRepository.save(loan);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public Optional<Loan> getLoanDetails(String loanId) {
		// TODO Auto-generated method stub
		return loanRepository.findById(loanId);
	}

	@Override
	public List<Loan> getLoanDetailsByAccountId(String accountId) {
		// TODO Auto-generated method stub
		return loanRepository.findByAccountId(accountId);
	}

	public List<Loan> getAllLoans() {
		return (List<Loan>) loanRepository.findAll();
	}

	public boolean updateLoanStatus(String loanId, LoanStatus status) {
		Optional<Loan> loanOptional = loanRepository.findById(loanId);

		if (loanOptional.isPresent()) {
			Loan loan = loanOptional.get();
			loan.setLoanStatus(status);
			loanRepository.save(loan);
			return true;
		}
		return false;
	}

	public List<Loan> getLoansByStatus(LoanStatus status) {
		return loanRepository.findByLoanStatus(status);
	}

}
