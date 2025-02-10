package com.dnb.accountservice.dto;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dnb.accountservice.enums.AccountType;
import com.dnb.accountservice.utils.CustomAccountIdGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
	@GenericGenerator(name = "account_seq", strategy = "com.dnb.accountservice.utils.CustomAccountIdGenerator",
	parameters = {@Parameter(name = CustomAccountIdGenerator.INCREMENT_PARAM, value = "1"),
	@Parameter(name = CustomAccountIdGenerator.VALUE_PREFIX_PARAMETER, value = "account_"),
	@Parameter(name = CustomAccountIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
	private String accountId;
	
	@Enumerated
	private AccountType accountType;
	
	private Double accountBalance;
	
	@NotBlank(message = "invalid pan card number")
	private String pancardNumber;

	@NotBlank(message = "invalid aadhar card number")
	private String aadharcardNumber;
	
	private String accountStatus;
 
	private String createDateTime;
	
	private int userId;

}
