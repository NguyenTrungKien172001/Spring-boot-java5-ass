package com.ass.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.entity.User;
import com.ass.repositories.UserRepository;
import com.ass.ultis.HashUtil;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private HttpServletRequest request;

	@GetMapping("/login")
	public String getLoginForm() {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "/auth/login";
	}
	
	@RequestMapping("/pageError")
	public String getError() {
		return "auth/error";
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email, 
			@RequestParam("password") String password
	) {
		User entity = this.userRepo.findByEmail(email);
		HttpSession session = request.getSession();
		
		if(entity == null) {
			session.setAttribute("error", "Sai Email hoac Password");
			return "redirect:/login";
		}
		
		boolean checkPwd = HashUtil.verify(password, entity.getPassword());
		if (!checkPwd) {
			session.setAttribute("error", "Sai Email hoac Password");
			return "redirect:/login";
		}
		
		session.setAttribute("user", entity);
		
		if(entity.getAdmin()==0) {
			return "redirect:/users/home";
		}else {
			return "redirect:/admin/users";
		}
		
	}
}
