package com.dnb.apigatewayservice.exception;

import org.springframework.security.core.AuthenticationException;

public class UnsupportedJwtException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public UnsupportedJwtException(String msg) {

		super(msg);

	}

}
