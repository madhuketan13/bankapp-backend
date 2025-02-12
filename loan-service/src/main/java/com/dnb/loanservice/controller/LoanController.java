package com.dnb.loanservice.controller;

import java.util.List;
import java.util.Map;

import com.dnb.loanservice.enums.LoanStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/getAllLoans")
	public ResponseEntity<List<Loan>> getAllLoans() {
		List<Loan> loans = loanService.getAllLoans();
		return ResponseEntity.ok(loans);
	}

	@GetMapping("/pending")
	public ResponseEntity<List<Loan>> getPendingLoans() {
		List<Loan> pendingLoans = loanService.getLoansByStatus(LoanStatus.PENDING);
		return ResponseEntity.ok(pendingLoans);
	}

	@PutMapping("/{loanId}/status")
	public ResponseEntity<?> updateLoanStatus(
			@PathVariable String loanId,
			@RequestParam String status) {

		try {
			LoanStatus loanStatus = LoanStatus.valueOf(status.toUpperCase());

			boolean isUpdated = loanService.updateLoanStatus(loanId, loanStatus);

			if (isUpdated) {
				return ResponseEntity.ok(Map.of("message", "Loan status updated successfully"));
			} else {
				return ResponseEntity.badRequest().body("Invalid Loan ID");
			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Invalid loan status: " + status);
		}
	}

}
