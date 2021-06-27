package com.ass.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ass.dto.UserDTO;
import com.ass.entity.User;
import com.ass.mapper.UserMapper;
import com.ass.repositories.UserRepository;
import com.ass.ultis.HashUtil;

@Controller
public class CreateNewAcc {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserMapper mapper;
	
	//them du lieu
	@GetMapping(value = "/createNew")
	public String create(Model model, UserDTO userDTO) {
		model.addAttribute("user", userDTO);
		return "auth/createnewAcc";
	}

	@PostMapping(value = "/storeNew")
	public String store(Model model, @Valid @ModelAttribute("user") UserDTO user, BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println("Có lỗi"+result.getAllErrors());
			model.addAttribute("errors", result.getAllErrors());
//			model.addAttribute("user", user);
			return "auth/createnewAcc";
		} else {
			User entity = this.mapper.convertToEntity(user);
			//ma hoa pass
			String hashedPassword = HashUtil.hash(entity.getPassword());
			entity.setPassword(hashedPassword);
			
			this.userRepo.save(entity);
			return "redirect:/login";
		}
	}				
}
