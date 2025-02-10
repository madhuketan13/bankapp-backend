package com.dnb.loanservice.dto;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dnb.loanservice.enums.LoanStatus;
import com.dnb.loanservice.enums.LoanType;
import com.dnb.loanservice.utils.CustomLoanIdGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_seq")
	@GenericGenerator(name = "loan_seq", strategy = "com.dnb.loanservice.utils.CustomLoanIdGenerator",
	parameters = {@Parameter(name = CustomLoanIdGenerator.INCREMENT_PARAM, value = "1"),
	@Parameter(name = CustomLoanIdGenerator.VALUE_PREFIX_PARAMETER, value = "loan_"),
	@Parameter(name = CustomLoanIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
	private String loanId;

	@ElementCollection(targetClass = LoanType.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<LoanType> loanType;

	private int loanAmount;

	@Enumerated
	private LoanStatus loanStatus = LoanStatus.PENDING;

	private String appliedDate;

	private String accountId;

}
