package com.study.skeletonPj.common.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	public LoginFailureHandler(SessionRegistry sessionRegistry) {
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException {

		String errorMsg = getExceptionMessage(authenticationException);
		
		request.getSession().setAttribute("errorMsg", errorMsg);
		response.sendRedirect("/login");
		
		authenticationException.printStackTrace();
		System.err.print("errorMsg : " + errorMsg);
		writePrintErrorResponse(response, authenticationException);
	}

	private void writePrintErrorResponse(HttpServletResponse response, AuthenticationException exception) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			Map<String, Object> responseMap = new HashMap<>();

			String message = getExceptionMessage(exception);

			responseMap.put("status", 401);

			responseMap.put("message", message);

			response.getOutputStream().println(objectMapper.writeValueAsString(responseMap));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getExceptionMessage(AuthenticationException exception) {
		if (exception instanceof BadCredentialsException) {
			return "비밀번호불일치";
		} else if (exception instanceof UsernameNotFoundException) {
			return "계정없음";
		} else if (exception instanceof AccountExpiredException) {
			return "계정만료";
		} else if (exception instanceof CredentialsExpiredException) {
			return "비밀번호만료";
		} else if (exception instanceof DisabledException) {
			return "계정비활성화";
		} else if (exception instanceof LockedException) {
			return "계정잠김";
		} else if (exception instanceof InternalAuthenticationServiceException){
			return "존재하지 않는 아이디";
		} else {
			return "알 수 없는 에러 발생";
		}
	}
}
