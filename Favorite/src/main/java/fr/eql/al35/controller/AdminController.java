package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eql.al35.entity.Product;
import fr.eql.al35.service.ProductIService;

@Controller
public class AdminController {

	@Autowired
	ProductIService productService;

	@GetMapping("/admin/product")
	public String displayCartProduct( Model model) {
		model.addAttribute("products", productService.displayAllProducts());
		model.addAttribute("product", new Product());
		return "adminProduct";
	}
	
	@GetMapping("/admin/home")
	public String redirectAdminHome( Model model) {
		return "adminHome";
	}
	
	@PostMapping("/upDateProducts")
	public String upDateProducts(@ModelAttribute("product")Product product, Model model) {
		System.out.println("================================" + product.toString());
		
		productService.upDate(product);
		System.out.println("================================" + product.toString());
		model.addAttribute("products", productService.displayAllProducts());

		return "adminProduct";
	}



}
