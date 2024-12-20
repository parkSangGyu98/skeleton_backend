package com.study.skeletonPj.common.config.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.study.skeletonPj.api.user.service.UserService;
import com.study.skeletonPj.common.handler.CustomAccessDeniedHandler;
import com.study.skeletonPj.common.handler.CustomAuthenticationFailureHandler;
import com.study.skeletonPj.common.handler.CustomAuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	private final UserService userService;
	
	private static final String[] SWAGGER_URL_ARRAY = {
		"/swagger-ui/**", "/api-docs", "/swagger-ui.html"
	};
	
	private static final String[] PERMIT_URL_ARRAY = {
		"/login", "/signup", "/regist"
	};
	
	private static final String[] ASSET_URL_ARRAY = {
		"/css/**", "/font/**", "/img/**", "/js/**", "startbootstrap/**"
	};
	
	private static final String[] API_URL_ARRAY = {
		"/api/**",
	};
	
	// 스프링 시큐리티 기능 비활성화
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
				.requestMatchers("/static/**");
	}
	
	@Bean
	CustomAuthenticationSuccessHandler CustomAuthenticationSuccessHandler() {
		return new com.study.skeletonPj.common.handler.CustomAuthenticationSuccessHandler(sessionRegistry());
	}

	@Bean
	CustomAuthenticationFailureHandler CustomAuthenticationFailureHandler() {
		return new com.study.skeletonPj.common.handler.CustomAuthenticationFailureHandler(sessionRegistry());
	}

	@Bean
	SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	// 특정 HTTP 요청에 대한 웹 기반 보안 구성
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// csrf 보안 설정 비활성화
		//http.csrf(csrf ->csrf.disable());
		http.csrf(withDefaults());

		http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
		
		// 접근 및 권한 처리
		http.authorizeHttpRequests(requests ->requests
				.requestMatchers(PERMIT_URL_ARRAY).permitAll()
				.requestMatchers(ASSET_URL_ARRAY).permitAll()
				.requestMatchers(API_URL_ARRAY).permitAll()
				.requestMatchers(SWAGGER_URL_ARRAY).hasAnyAuthority("ADMIN")
				.anyRequest().authenticated());
		
		// 폼 기반 로그인 설정
		http.formLogin(login ->login 
				.loginPage("/login") // form 에 있는 POST 매핑 주소
				.successHandler(CustomAuthenticationSuccessHandler())
				.failureHandler(CustomAuthenticationFailureHandler()));
		
		// 로그아웃 관련 처리
		http.logout(logout ->logout
				.logoutUrl("/logout") // 로그아웃 URL 설정
				.logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 URL 설정
				.invalidateHttpSession(true) // 로그아웃 후 세션 초기화 설정
				.deleteCookies("JSESSIONID")); // 로그아웃 후 쿠기 삭제 설정
				
		http.sessionManagement(sessionManagement-> sessionManagement
				.maximumSessions(1)
				.expiredUrl("/login")
				.maxSessionsPreventsLogin(true) // 다른곳에서 로그인하면 나머지 세션 없앰
				.sessionRegistry(sessionRegistry()));
		
		
		//접근 거부 커스텀 핸들러 처리
		http.exceptionHandling(handling -> handling
				.accessDeniedHandler(new CustomAccessDeniedHandler()));
		
		
		return http.build();
	}
	
	// 인증 관리자 관련 설정
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(userService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

		return daoAuthenticationProvider;
	}
	
	// 패스워드 인코더로 사용할 빈 등록
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}