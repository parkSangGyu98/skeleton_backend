package com.study.skeletonPj.common.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

	public LoginFailureHandler(SessionRegistry sessionRegistry) {
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
		log.error("로그인 실패");
		log.error("===========================================================================================\n");
		String errorMsg = "";

		if (authenticationException instanceof BadCredentialsException) {
			errorMsg = "입력하신 정보를 찾을 수 없습니다. 확인 후 다시 입력하세요.";
		}else if (authenticationException instanceof LockedException) {
			errorMsg = "잠긴 계정입니다. 관리자에게 문의하세요.";
		}else if (authenticationException instanceof DisabledException) {
			errorMsg = "계정정보에 문제가 있습니다. 관리자에게 문의하세요.";
		}else if (authenticationException instanceof SessionAuthenticationException){
//			String username = request.getParameter("username");
//			String password = request.getParameter("password");
//			String isForceLogin = request.getParameter("isForceLogin");
//
//			errorMsg = "다른 장소에서 로그인 중입니다. 다른곳에 로그인 되어있는 ";
//			if("true".equals(isForceLogin)) {
//			if(true) {
//				MemberMaster.Veo userDetails = memberMasterService.loadUserByUsername(username);
//				List<SessionInformation> sessions = sessionRegistry.getAllSessions(userDetails, false);
//				for (SessionInformation session : sessions) {
//					session.expireNow();
//				}
//
//				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//				SecurityContextHolder.getContext().setAuthentication(authentication);
//
//				request.setAttribute("errorMsg", errorMsg);
//				request.getRequestDispatcher("/main").forward(request, response);
//
//				return;
//			}
			errorMsg = "계정정보에 문제가 있습니다. 관리자에게 문의하세요.";
		}else {
			log.error(authenticationException.toString());
			log.error("===========================================================================================\n");
		}

		 // 에러 메시지를 세션에 저장
		request.getSession().setAttribute("errorMsg", errorMsg);

		//login 페이지로 이동
		response.sendRedirect("/login");

	}

}
