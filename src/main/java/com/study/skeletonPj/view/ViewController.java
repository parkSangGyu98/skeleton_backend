package com.study.skeletonPj.view;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.skeletonPj.api.user.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@Controller
public class ViewController {
	
	@RequestMapping("/")
	public String root(HttpServletRequest request, Model model, @AuthenticationPrincipal User.Veo userInfo, HttpSession session) throws Exception {
		return home(request, model, userInfo, session);
	}
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model, @AuthenticationPrincipal User.Veo userInfo, HttpSession session) throws Exception {
		String userId = session.getAttribute("USER_ID").toString();
		
		if(userId.equals(null) || userId.equals("")) {
			System.out.println("USER_ID @@@@@ : " + session.getAttribute("USER_ID"));
			return "redirect:/login";
		}
		System.out.println("USER_ID : " + session.getAttribute("USER_ID"));
		System.out.println("USER_AUTH : " + session.getAttribute("USER_AUTH"));
		return "project/index";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model, @AuthenticationPrincipal User.Veo userInfo, HttpSession session) {
		session.invalidate();
		String errorMsg = (String) request.getSession().getAttribute("errorMsg");
		model.addAttribute("errorMsg", errorMsg);
		
		return "project/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
				SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/login";
	}
	
	@RequestMapping("/regist")
	public String signup() {
		return "project/regist";
	}
	
	@RequestMapping("/charts")
	public String charts() {
		return "project/charts";
	}
	
	@RequestMapping("/tables")
	public String tables() {
		return "project/tables";
	}
	
	@RequestMapping("/layout-sidenav-light")
	public String layoutSidenavLight() {
		return "project/layout-sidenav-light";
	}
	
	@RequestMapping("/layout-static")
	public String layoutStatic() {
		return "project/layout-static";
	}
	
	@RequestMapping("/password")
	public String password() {
		return "project/password";
	}
	
	@RequestMapping("/401")
	public String error401() {
		return "project/error/401";
	}
	
	@RequestMapping("/404")
	public String error404() {
		return "project/error/404";
	}
	
	@RequestMapping("/500")
	public String error500() {
		return "project/error/500";
	}
	
}
