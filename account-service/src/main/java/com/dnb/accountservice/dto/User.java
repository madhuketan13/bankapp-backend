package com.dnb.accountservice.dto;

import lombok.Data;

@Data
public class User {
	
	private int userId;
	private String userName;
	private String emailAddress;
	private String password;
	
}
