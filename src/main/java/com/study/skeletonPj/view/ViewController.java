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
		return "home/index";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model, @AuthenticationPrincipal User.Veo userInfo) {
		String errormsg = (String) request.getSession().getAttribute("errormsg");
		model.addAttribute("errormsg", errormsg);
		
		return "home/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
				SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/login";
	}
	
	@RequestMapping("/register")
	public String signup() {
		return "home/register";
	}
	
	@RequestMapping("/charts")
    public String charts() {
        return "home/charts";
    }
	
	@RequestMapping("/tables")
    public String tables() {
        return "home/tables";
    }
	
	@RequestMapping("/layout-sidenav-light")
    public String layoutSidenavLight() {
        return "home/layout-sidenav-light";
    }
	
	@RequestMapping("/layout-static")
    public String layoutStatic() {
        return "home/layout-static";
    }
	
	@RequestMapping("/password")
    public String password() {
        return "home/password";
    }
	
	@RequestMapping("/register")
    public String register() {
        return "home/register";
    }
	
	@RequestMapping("/401")
    public String error401() {
        return "home/error/401";
    }
	
	@RequestMapping("/404")
    public String error404() {
        return "home/error/404";
    }
	
	@RequestMapping("/500")
    public String error500() {
        return "home/error/500";
    }
	
}
