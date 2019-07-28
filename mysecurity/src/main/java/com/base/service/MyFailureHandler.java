package com.base.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class MyFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse rep, AuthenticationException arg2)
			throws IOException, ServletException {
		rep.setCharacterEncoding("UTF-8");
		rep.setContentType("application/json");
		rep.getWriter().write("{\"code\":403,\"message\":\"账户密码错误\",\"data\":\"\"}");
		rep.getWriter().flush();
	}

}
