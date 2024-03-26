package com.study.skeletonPj.common.handler;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException {
		//커스텀 접근 거부 처리 로직 (403 페이지 만들어서 보내주자)
		System.out.println("#######################################################################");
		System.out.println("접근 거부 발생");
		System.out.println("#######################################################################");
	}
}