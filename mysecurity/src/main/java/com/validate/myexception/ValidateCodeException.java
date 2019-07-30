package com.validate.myexception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException{

	public ValidateCodeException(String msg) {
        super(msg);
    }

    private static final long serialVersionUID = 1422465195260228715L;
}
