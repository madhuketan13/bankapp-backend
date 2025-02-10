package com.dnb.apigatewayservice.exception;

import org.springframework.security.core.AuthenticationException;

public class SignatureException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public SignatureException(String msg) {

	super(msg);

}

}
