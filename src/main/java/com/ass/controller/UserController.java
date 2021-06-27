package com.ass.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.mapper.UserMapper;
import com.ass.repositories.UserRepository;
import com.ass.ultis.HashUtil;
import com.ass.dto.UserDTO;
import com.ass.entity.User;

@Controller
@RequestMapping(value = "/admin/users")
public class UserController {
	@Autowired
	private HttpServletRequest request;

	@Autowired
//	@Qualifier
	private UserRepository userRepo;

	@Autowired
	private UserMapper mapper;
	
	// lay du lieu trong database
	
		@GetMapping()
		public String index(Model model) {
			String sortBy = request.getParameter("sort_by");
			String sortDirection = request.getParameter("sort_direction");
			String pageParam = request.getParameter("page");
			String limitParam = request.getParameter("limit");

			String sortField = sortBy == null ? "id" : sortBy;
			Sort sort = (sortDirection == null || sortDirection.equals("asc")) ? Sort.by(Direction.ASC, sortField)
					: Sort.by(Direction.DESC, sortField);
			//asc tang
			int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
			int limit = limitParam == null ? 3 : Integer.parseInt(limitParam);
			Pageable pageable = PageRequest.of(page, limit, sort);

			Page<User> pageData = this.userRepo.findByActivated1(pageable);

			model.addAttribute("pageData", pageData);
			
			String sortPage = sortDirection==null?"asc":sortDirection;
			model.addAttribute("sort",sortPage);
			return "admin/users/index";
		}
		
		//them du lieu
		@GetMapping(value = "/create")
		public String create(Model model, UserDTO userDTO) {
			model.addAttribute("user", userDTO);
			return "admin/users/create";
		}

		@PostMapping(value = "/store")
		public String store(Model model, @Valid @ModelAttribute("user") UserDTO user, BindingResult result) {
			if (result.hasErrors()) {
				System.out.println("Có lỗi");
				model.addAttribute("errors", result.getAllErrors());
//				model.addAttribute("user", user);
				return "admin/users/create";
			} else {
				User entity = this.mapper.convertToEntity(user);
				//ma hoa pass
				String hashedPassword = HashUtil.hash(entity.getPassword());
				entity.setPassword(hashedPassword);
				
				this.userRepo.save(entity);
				return "redirect:/admin/users";
			}
		}		
		
		//update du lieu
		@GetMapping(value = "/edit/{id}")
		public String edit(Model model, @PathVariable("id") User entity) {

			UserDTO user = this.mapper.convertToDTO(entity);
			model.addAttribute("user", user);
			
			return "admin/users/edit";
		}

		@PostMapping(value = "/update/{id}")
		public String update(Model model, @Valid @ModelAttribute("user") UserDTO user, BindingResult result) {
			if (result.hasErrors()) {
				System.out.println("Có lỗi");
				model.addAttribute("errors", result.getAllErrors());
				model.addAttribute("user", user);
				return "admin/users/edit";
			} else {
				User entity = this.mapper.convertToEntity(user);
				this.userRepo.save(entity);
				return "redirect:/admin/users";
			}
		}
		
		//xoa du lieu mềm
		@PostMapping(value = "/delete/{id}")
		public String delete(@PathVariable("id") Integer id) {
			User entity = this.userRepo.getOne(id);
			entity.setActivated(0);
			this.userRepo.save(entity);
			return "redirect:/admin/users";
		}
		
		
//changepw		
		@GetMapping("/changePW")
		public String getChangePWForm(Model model) {
			HttpSession session = request.getSession();
			User entity =  (User) session.getAttribute("user");
//			request.setAttribute("userCpw", entity);
			model.addAttribute("userCpw", entity);
			return "admin/common/changePW";
		}

		@PostMapping("/changePW")
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
				return "redirect:/admin/users/changePW";
			}
			
			boolean checkPwd = HashUtil.verify(password, entity.getPassword());
			if (!checkPwd) {
				session.setAttribute("error", "Sai Password");
				return "redirect:/admin/users/changePW";
			}
////			
//			if(newpassword==null) {
//				session.setAttribute("error", "Nhập New Password");
//				return "redirect:/admin/users/changePW";
//			}
////			
//			if(CPpassword==null) {
//				session.setAttribute("error", "Nhập Confirm Password");
//				return "redirect:/admin/users/changePW";
	//
//			}
////			
//			if(!CPpassword.equals(newpassword)) {
//				session.setAttribute("error", "Nhập Confirm Password giống New PW");
//				return "redirect:/admin/users/changePW";
//			}
			
//			session.setAttribute("error", "");
			
			//ma hoa pass
			System.out.println(CPpassword);
			String hashedPassword = HashUtil.hash(CPpassword);
			
			entity.setPassword(hashedPassword);
			
			this.userRepo.save(entity);
			
			
			return "redirect:/admin/users";
			
			
		}
		
//thùng rác
		@GetMapping("/bin")
		public String Bin(Model model) {
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

			Page<User> pageData = this.userRepo.findByActivated(pageable);
			

			model.addAttribute("pageData", pageData);
			String sortPage = sortDirection==null?"asc":sortDirection;
			model.addAttribute("sort",sortPage);
			return "admin/bin/usersBin";
		}
		
		//khôi phục
		@PostMapping(value = "/bin/restore/{id}")
		public String restore(@PathVariable("id") Integer id) {
			User entity = this.userRepo.getOne(id);
			entity.setActivated(1);
			this.userRepo.save(entity);
			return "redirect:/admin/users/bin";
		}
		//xóa vĩnh viễn
		@PostMapping(value = "/bin/delete/{id}")
		public String permanentlyDeleted(@PathVariable("id") Integer id) {
			this.userRepo.deleteById(id);
			return "redirect:/admin/users/bin";
		}		
}
