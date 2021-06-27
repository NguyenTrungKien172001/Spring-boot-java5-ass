package com.ass.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.ass.dto.CategoryDTO;
import com.ass.entity.Category;
import com.ass.mapper.CategoryMapper;
import com.ass.repositories.CategoryRepository;

@Controller
@RequestMapping(value = "/admin/categories")
public class CategoryController {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private CategoryMapper mapper;
	
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

		int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
		int limit = limitParam == null ? 3 : Integer.parseInt(limitParam);
		Pageable pageable = PageRequest.of(page, limit, sort);

		Page<Category> pageData = this.categoryRepo.findByActivated1(pageable);

		model.addAttribute("pageData", pageData);
		
		String sortPage = sortDirection==null?"asc":sortDirection;
		model.addAttribute("sort",sortPage);
		
		return "admin/categories/index";
	}
	
	//them du lieu
	@GetMapping(value = "/create")
	public String create(Model model, CategoryDTO categoryDTO) {
		model.addAttribute("category", categoryDTO);
		return "admin/categories/create";
	}

	@PostMapping(value = "/store")
	public String store(Model model, @Valid @ModelAttribute("category") CategoryDTO category, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Có lỗi");
			model.addAttribute("errors", result.getAllErrors());
			return "admin/categories/create";
		} else {
			Category entity = this.mapper.convertToEntity(category);
			this.categoryRepo.save(entity);
			return "redirect:/admin/categories";
		}
	}
	//update du lieu
	@GetMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") Category entity) {

		CategoryDTO category = this.mapper.convertToDTO(entity);
		model.addAttribute("category", category);
		return "admin/categories/edit";
	}

	@PostMapping(value = "/update/{id}")
	public String update(Model model, @Valid @ModelAttribute("category") CategoryDTO category, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Có lỗi");
			model.addAttribute("errors", result.getAllErrors());
			model.addAttribute("category", category);
			return "admin/categories/edit";
		} else {
			Category entity = this.mapper.convertToEntity(category);
			this.categoryRepo.save(entity);
			return "redirect:/admin/categories";
		}
	}
	//xoa du lieu mềm
	@PostMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
//		this.categoryRepo.deleteById(id);
		Category entity = this.categoryRepo.getOne(id);
		entity.setActivate(0);
		this.categoryRepo.save(entity);
		return "redirect:/admin/categories";
	}
// thung rac
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

		Page<Category> pageData = this.categoryRepo.findByActivated(pageable);

		model.addAttribute("pageData", pageData);
		
		String sortPage = sortDirection==null?"asc":sortDirection;
		model.addAttribute("sort",sortPage);
		
		return "admin/bin/categoryBin";
	}
	
	//khôi phục
	@PostMapping(value = "/bin/restore/{id}")
	public String restore(@PathVariable("id") Integer id) {
		Category entity = this.categoryRepo.getOne(id);
		entity.setActivate(1);
		this.categoryRepo.save(entity);
		return "redirect:/admin/categories/bin";
	}
	//xóa vĩnh viễn
	@PostMapping(value = "/bin/delete/{id}")
	public String permanentlyDeleted(@PathVariable("id") Integer id) {
		this.categoryRepo.deleteById(id);
		return "redirect:/admin/categories/bin";
	}
	
}
