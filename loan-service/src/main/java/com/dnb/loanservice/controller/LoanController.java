package com.dnb.loanservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.loanservice.dto.Loan;
import com.dnb.loanservice.exceptions.IdNotFoundException;
import com.dnb.loanservice.payload.request.LoanRequest;
import com.dnb.loanservice.service.LoanService;
import com.dnb.loanservice.utils.LoanRequestToEntity;


@RestController
@RequestMapping("/api/loan")
public class LoanController {
	
	@Autowired
	LoanRequestToEntity mapper;
	
	@Autowired
	LoanService loanService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createLoan(@RequestBody LoanRequest loan) throws IdNotFoundException {
		
		Loan loanRequest = mapper.getLoanEntity(loan);
		
		Loan loanData = loanService.createLoan(loanRequest);
		
		return new ResponseEntity(loanData, HttpStatus.CREATED);

	}
	
	@GetMapping("/getLoan/{accountId}")
	public ResponseEntity<?> getLoanDetailsByAccountId(@PathVariable("accountId") String accountId) throws IdNotFoundException{
		
		List<Loan> listOfLoans = loanService.getLoanDetailsByAccountId(accountId);
		
		return ResponseEntity.ok(listOfLoans);
	}

}
