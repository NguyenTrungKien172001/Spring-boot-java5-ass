package com.ass.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ass.entity.User;

@Component
public class AuthenticateInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler
	) throws IOException {
		
		HttpSession session = request.getSession();
		
		User entity = (User) session.getAttribute("user");
		
		String uri = request.getRequestURI();
		
		if ( entity == null ) {
			session.setAttribute("error", "Vui lòng đăng nhập");
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}else if(entity.getAdmin()==0 && uri.startsWith(request.getContextPath()+"/admin")) {
			response.sendRedirect(request.getContextPath() + "/pageError");
			return false;
		}else if(entity.getAdmin()==1 && uri.startsWith(request.getContextPath()+"/users")) {
			response.sendRedirect(request.getContextPath() + "/pageError");
			return false;
		}else {
			return true;
		}
		
	}
}