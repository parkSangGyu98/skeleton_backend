package com.study.skeletonPj.view;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.skeletonPj.api.user.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Controller
public class ViewController {
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "home";
	}
	
	@RequestMapping("/home")
	public String home(Model model, @AuthenticationPrincipal User.Veo userInfo) throws Exception {
	    model.addAttribute("userInfo", userInfo);
	    return "home";
	}
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
    
	@RequestMapping("/signup")
    public String signup() {
        return "signup";
    }
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    new SecurityContextLogoutHandler().logout(request, response,
	            SecurityContextHolder.getContext().getAuthentication());
	    return "redirect:/login";
	}
	
	/* bootstrap test {S} */
	@RequestMapping("bootstrap/index")
    public String bootstrapTest() {
        return "bootstrap/index";
    }
	
	@RequestMapping("bootstrap/charts")
    public String charts() {
        return "bootstrap/charts";
    }
	
	@RequestMapping("bootstrap/tables")
    public String tables() {
        return "bootstrap/tables";
    }
	
	@RequestMapping("bootstrap/layout-sidenav-light")
    public String layoutSidenavLight() {
        return "bootstrap/layout-sidenav-light";
    }
	
	@RequestMapping("bootstrap/layout-static")
    public String layoutStatic() {
        return "bootstrap/layout-static";
    }
	
	@RequestMapping("bootstrap/login")
    public String bootstrapLogin() {
        return "bootstrap/login";
    }
	
	@RequestMapping("bootstrap/password")
    public String password() {
        return "bootstrap/password";
    }
	
	@RequestMapping("bootstrap/register")
    public String register() {
        return "bootstrap/register";
    }
	
	@RequestMapping("bootstrap/401")
    public String error401() {
        return "bootstrap/error/401";
    }
	
	@RequestMapping("bootstrap/404")
    public String error404() {
        return "bootstrap/error/404";
    }
	
	@RequestMapping("bootstrap/500")
    public String error500() {
        return "bootstrap/error/500";
    }
	
	/* bootstrap test {E} */
}
