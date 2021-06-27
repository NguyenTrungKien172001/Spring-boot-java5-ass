package com.ass.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ass.entity.Order;
import com.ass.entity.Order_detalls;
import com.ass.entity.User;
import com.ass.mapper.OrderMapper;
import com.ass.repositories.OrderRepository;
import com.ass.repositories.Order_detallsRepositories;
import com.ass.repositories.ProductRepository;



@Controller
@RequestMapping("/users/home")
public class ProductInvoiceUser {
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	Order_detallsRepositories order_detallsRepo;
	@Autowired
	ProductRepository proRepo;
	
	@GetMapping("/productInvoice")
	public String index(Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String sortBy = request.getParameter("sort_by");
		String sortDirection = request.getParameter("sort_direction");
		String pageParam = request.getParameter("page");
		String limitParam = request.getParameter("limit");

		String sortField = sortBy == null ? "id" : sortBy;
		Sort sort = (sortDirection == null || sortDirection.equals("asc")) ? Sort.by(Direction.ASC, sortField)
				: Sort.by(Direction.DESC, sortField);

		int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
		int limit = limitParam == null ? 3 : Integer.parseInt(limitParam);
		Pageable pageable = PageRequest.of(page, limit, sort);

		Page<Order> pageData =  this.orderRepo.findByUser_id(user.getId(),pageable);

		model.addAttribute("pageData", pageData);
		
		String sortPage = sortDirection==null?"asc":sortDirection;
		model.addAttribute("sort",sortPage);
		return "users/page/productInvoice";
	}
	
	@GetMapping("/productInvoice/{id}")
	public String views(Model model,@PathVariable("id") Integer id) {
		
		String pageParam = request.getParameter("page");
		String limitParam = request.getParameter("limit");

		int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
		int limit = limitParam == null ? 3 : Integer.parseInt(limitParam);
		Pageable pageable = PageRequest.of(page, limit);

		Page<Order_detalls> pageData =  this.order_detallsRepo.findByOrder_id(id, pageable);
		model.addAttribute("pageData", pageData);
		model.addAttribute("id",id);
		return "users/page/productInvoiceViews";
	}
}
