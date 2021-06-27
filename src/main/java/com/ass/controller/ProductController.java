package com.ass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ass.dto.ProductDTO;
import com.ass.entity.Category;
import com.ass.entity.Product;
import com.ass.mapper.ProductMapper;
import com.ass.repositories.CategoryRepository;
import com.ass.repositories.ProductRepository;

@Controller
@RequestMapping(value = "/admin/products")
public class ProductController {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ProductMapper mapper;
	
	@Autowired
	private CategoryRepository categoryRepo;

	
	@GetMapping()
	public String index(Model model) {
		//list danh muc
		List<Category> cate = this.categoryRepo.findByCategiryActivate1();
		model.addAttribute("categoryData", cate);
		
		String dmid = request.getParameter("dm") ;
		String dmField = dmid == null ? "" : dmid;
		
		List<Product> pro;
		if(dmField==null || dmField=="") {
			pro = this.productRepo.findByProductActivate();
		}else {
			pro = this.productRepo.findBycategory_id(Integer.parseInt(dmField));
		}
		//san pham

		model.addAttribute("tesst", pro);

		return "admin/products/index";
	}
	
	//them du lieu
	@GetMapping(value = "/create")
	public String create(Model model, ProductDTO productDTO) {
		//list danh muc
		List<Category> cate = this.categoryRepo.findByCategiryActivate1();
		model.addAttribute("categoryData", cate);
		//
		model.addAttribute("product", productDTO);
		model.addAttribute("date",java.time.LocalDate.now()+"");
		return "admin/products/create";
	}

	@PostMapping(value = "/store")
	public String store(Model model, @Valid @ModelAttribute("product") ProductDTO product, BindingResult result) {
		//list danh muc
		List<Category> cate = this.categoryRepo.findByCategiryActivate1();
		model.addAttribute("categoryData", cate);	

//		System.out.println(product.getCategoryId()+"/"+product.getCreateDate()+"/"+product.getImage()+"/"+product.getName()+"/"+product.getPrice()+"/"+product.getAvailable());

		if (result.hasErrors()) {
			System.out.println("Có lỗi"+result.getAllErrors());
			model.addAttribute("errors", result.getAllErrors());
			
			return "admin/products/create";
		} else {
			Product entity = this.mapper.convertToEntity(product);
			
			this.productRepo.save(entity);
			return "redirect:/admin/products";
		}
	}		
	
	//update du lieu
	@GetMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") Product entity) {
		//list danh muc
		List<Category> cate = this.categoryRepo.findByCategiryActivate1();
		model.addAttribute("categoryData", cate);
		
		ProductDTO product = this.mapper.convertToDTO(entity);
		model.addAttribute("product", product);
		
		return "admin/products/edit";
	}

	@PostMapping(value = "/update/{id}")
	public String update(Model model, @Valid @ModelAttribute("product") ProductDTO product, BindingResult result) {
//		System.out.println(product.getCategoryId()+"/"+product.getCreateDate()+"/"+product.getImage()+"/"+product.getName()+"/"+product.getPrice()+"/"+product.getAvailable());

		if (result.hasErrors()) {
			System.out.println("Có lỗi"+result.getAllErrors());
			model.addAttribute("errors", result.getAllErrors());
			model.addAttribute("product", product);
			return "admin/products/edit";
		} else {
			Product entity = this.mapper.convertToEntity(product);
			this.productRepo.save(entity);
			return "redirect:/admin/products";
		}
	}		
	
	
	//xoa du lieu mềm
	@PostMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
//		this.productRepo.deleteById(id);
		Product entity = this.productRepo.getOne(id);
		entity.setActivate(0);
		this.productRepo.save(entity);
		return "redirect:/admin/products";
	}
	
	//thùng rác
	@GetMapping("/bin")
	public String Bin(Model model) {
		//list danh muc

		List<Product> pro;

		pro = this.productRepo.findByProductActivate0();
		
		model.addAttribute("tesst", pro);

		return "admin/bin/productBin";
	}
	
	//khôi phục
	@PostMapping(value = "/bin/restore/{id}")
	public String restore(@PathVariable("id") Integer id) {
		Product entity = this.productRepo.getOne(id);
		entity.setActivate(1);
		this.productRepo.save(entity);
		return "redirect:/admin/products/bin";
	}
	
//	//xóa vĩnh viễn
	@PostMapping(value = "/bin/delete/{id}")
	public String permanentlyDeleted(@PathVariable("id") Integer id) {
		this.productRepo.deleteById(id);
		return "redirect:/admin/products/bin";
	}				
			
}
