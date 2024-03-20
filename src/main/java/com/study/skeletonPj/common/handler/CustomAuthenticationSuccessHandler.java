package com.study.skeletonPj.common.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private final SessionRegistry sessionRegistry;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			session.setAttribute("USER_ID", authentication.getName());
			//session.setAttribute("USER_AUTH", authentication.getAuthorities());
			System.out.println("test : " + authentication.getAuthorities());
			response.sendRedirect("/home");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CustomAuthenticationSuccessHandler(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}
}
