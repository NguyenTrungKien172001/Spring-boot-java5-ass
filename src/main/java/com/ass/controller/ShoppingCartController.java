package com.ass.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.dto.ItemDTO;
import com.ass.dto.OrderDTO;
import com.ass.entity.Order;
import com.ass.entity.Order_detalls;
import com.ass.entity.User;
import com.ass.mapper.OrderMapper;
import com.ass.repositories.OrderRepository;
import com.ass.repositories.Order_detallsRepositories;
import com.ass.repositories.ProductRepository;
import com.ass.ultis.ShoppingCartService;

@Controller
public class ShoppingCartController {
	@Autowired
	ShoppingCartService cart;
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

	@RequestMapping("/users/home/cart/view")
	public String view(Model model, OrderDTO orderDTO) {
		if(cart.getCount()==0) {
			model.addAttribute("mess", "Bạn chưa thêm sản phẩm nào");
		}else {
			model.addAttribute("mess", "");
		}
		
		HttpSession session = request.getSession();
		model.addAttribute("order", orderDTO);
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("cart", cart);
		return "users/page/cart";
	}

	@RequestMapping("/users/home/cart/add/{id}")
	public String add(@PathVariable("id") Integer id) {
		cart.add(id);
		return "redirect:/users/home";
	}

	@RequestMapping("/users/home/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cart.remove(id);
		return "redirect:/users/home/cart/view";
	}

	@RequestMapping("/users/home/cart/update/{id}")
	public String update(@PathVariable("id") Integer id, @RequestParam("quantity") Integer quantity) {
		cart.update(id, quantity);
		return "redirect:/users/home/cart/view";
	}
	
	@RequestMapping("/users/home/cart/clear")
	public String clear() {
		cart.clear();
		return "redirect:/users/home/cart/view";
	}
	
	@PostMapping(value = "/users/home/cart/order")
	public String order(Model model,@ModelAttribute("order") @Valid  OrderDTO order, BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println("Có lỗi");
			model.addAttribute("errors", result.getAllErrors());
			model.addAttribute("cart", cart);
			return "users/page/cart";
		} else {
			//save order
			Order entity = this.orderMapper.convertToEntity(order);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			entity.setActivate(1);
			entity.setUser(user);
			entity.setCreateDate(java.time.LocalDate.now()+"");
			entity.setPhone(order.getPhone().trim());
			this.orderRepo.save(entity);
			
			//save order detall
			for(ItemDTO item : cart.getItems()) {
				Order_detalls orderdetalls = new Order_detalls();
				orderdetalls.setOrder(entity);
				proRepo.findById(item.getId()).ifPresent(
					product -> {orderdetalls.setProduct(product);
				});
				orderdetalls.setPrice(item.getPrice());
				orderdetalls.setQuantity(item.getQuantity());
				this.order_detallsRepo.save(orderdetalls);
			}
			
			cart.clear();
			return "redirect:/users/home/cart/view";
		}
	}

}
