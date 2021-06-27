package com.ass.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ass.entity.Order;
import com.ass.repositories.OrderRepository;
import com.ass.repositories.ProductRepository;

@Controller
@RequestMapping("/admin/users")
public class ProductInvoiceAdmin {
	@Autowired
	HttpServletRequest request;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	ProductRepository proRepo;
	
	@GetMapping("/productInvoice")
	public String index(Model model) {
	
		
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

		Page<Order> pageData =  this.orderRepo.findAll(pageable);

		model.addAttribute("pageData", pageData);
		
		String sortPage = sortDirection==null?"asc":sortDirection;
		model.addAttribute("sort",sortPage);
		
		return "admin/order/productInvoiceAdmin";
	}
	
	
	@PostMapping("/productInvoice/{id}")
	public String update(Model model,@PathVariable("id") Integer id) {
		Order order = this.orderRepo.getOne(id);
		String value = request.getParameter("activate");
		order.setActivate(Integer.parseInt(value));
		this.orderRepo.save(order);
		return "redirect:/admin/users/productInvoice";
	}
}
