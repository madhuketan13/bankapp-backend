package com.dnb.apigatewayservice.exception;

import org.springframework.security.core.AuthenticationException;

public class IllegalArgumentException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public IllegalArgumentException(String msg) {

		super(msg);

	}

}
