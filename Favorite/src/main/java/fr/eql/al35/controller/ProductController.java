package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eql.al35.service.ProductIService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductIService productService;


	@GetMapping("/productList")
	public String testPage(Model model) {
		model.addAttribute("products", productService.displayAvailableProducts());
		return "productList";
	}
}
