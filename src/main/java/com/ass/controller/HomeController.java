package com.ass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.dto.ProductDTO;
import com.ass.entity.Category;
import com.ass.entity.Product;
import com.ass.entity.User;
import com.ass.mapper.ProductMapper;
import com.ass.repositories.CategoryRepository;
import com.ass.repositories.ProductRepository;
import com.ass.repositories.UserRepository;
import com.ass.ultis.HashUtil;

@Controller
@RequestMapping(value = "/users/home")
public class HomeController {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ProductMapper mapper;
	
	@GetMapping()
	public String index(Model model) {
		//list danh muc
		List<Category> cate = this.categoryRepo.findByCategiryActivate1();
		model.addAttribute("categoryData", cate);
		
		List<Product> pro;
		
		String keyword = request.getParameter("keyword");
		
		String dmid = request.getParameter("dm") ;
		String dmField = dmid == null ? "" : dmid;
		
		if(keyword!=null) {
			pro = this.productRepo.search(keyword);
		}else if(dmField==null || dmField=="" || keyword=="") {
			pro = this.productRepo.findByProductActivate();
		}else {
			pro = this.productRepo.findBycategory_id(Integer.parseInt(dmField));
		}

		if(pro.size()==0) {
			model.addAttribute("proNull", "Không có sản phẩm nào");
		}else {
			model.addAttribute("proNull", "");
		}
		model.addAttribute("keyword", keyword);
		model.addAttribute("tesst", pro);
		

		return "users/page/home";
	}
// show product	
	@GetMapping(value = "{id}")
	public String edit(Model model, @PathVariable("id") Product entity) {

		ProductDTO product = this.mapper.convertToDTO(entity);
		model.addAttribute("product", product);
		
		return "users/page/showProduct";
	}
// change pw
	@GetMapping("/changePassword")
	public String getChangePWForm(Model model) {
		HttpSession session = request.getSession();
		User entity =  (User) session.getAttribute("user");
		model.addAttribute("userCpw", entity);
		return "/auth/changePW";
	}

	@PostMapping("/changePassword")
	public String ChangePW(
			Model model,
			@RequestParam("email") String email, 
			@RequestParam("password") String password,
			@RequestParam("newpassword") String newpassword,
			@RequestParam("CPpassword") String CPpassword
	) {
		User entity = this.userRepo.findByEmail(email);
		HttpSession session = request.getSession();
		
		if(entity == null) {
			session.setAttribute("error", "Sai Email hoac Password");
			return "redirect:/users/home/changePassword";
		}
		
		boolean checkPwd = HashUtil.verify(password, entity.getPassword());
		if (!checkPwd) {
			session.setAttribute("error", "Sai Password");
			return "redirect:/users/home/changePassword";
		}
////		
//		if(newpassword==null) {
//			session.setAttribute("error", "Nhập New Password");
//			return "redirect:/users/home/changePassword";
//		}
////		
//		if(CPpassword==null) {
//			session.setAttribute("error", "Nhập Confirm Password");
//			return "redirect:/users/home/changePassword";
//
//		}
////		
//		if(!CPpassword.equals(newpassword)) {
//			session.setAttribute("error", "Nhập Confirm Password giống New PW");
//			return "redirect:/users/home/changePassword";
//		}
		
//		session.setAttribute("error", "");
		
		//ma hoa pass
		System.out.println(CPpassword);
		String hashedPassword = HashUtil.hash(CPpassword);
		
		entity.setPassword(hashedPassword);
		
		this.userRepo.save(entity);
		
		
		return "redirect:/users/home";
	}
	

	
}
