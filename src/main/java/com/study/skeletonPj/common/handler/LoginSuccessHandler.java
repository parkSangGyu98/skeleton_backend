package com.study.skeletonPj.common.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private final SessionRegistry sessionRegistry;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/home");
	}

	public LoginSuccessHandler(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}
}
