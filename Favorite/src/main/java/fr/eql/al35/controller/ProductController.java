package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.ProductType;
import fr.eql.al35.iservice.ProductIService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductIService productService;
	

	@GetMapping("/products/all")
	public String displayAllProducts(Model model) {
		model.addAttribute("products", productService.displayAvailableProducts());
		model.addAttribute("categories", productService.displayAllCategories());
		ProductType productType = new ProductType();
		productType.setName("");
		model.addAttribute("productType", productType);
		return "showcase";
	}
	
	@GetMapping("/products/{category}/{id}")
	public String displayProduct(@PathVariable String category, @PathVariable Integer id, Model model) {
		
		Article article = new Article();
		
		model.addAttribute("product", productService.displayProductById(id));
		model.addAttribute("categories", productService.displayAllCategories());
		model.addAttribute("article", article);
		
		return "productSheet";
	}
	@GetMapping("/products/{productType}")
	public String displayProductsByType(@PathVariable ProductType productType, Model model) {
		model.addAttribute("categories", productService.displayAllCategories());
		model.addAttribute("products", productService.displayByProductType(productType));
		model.addAttribute("productType", productType);
		return "showcase";
	}
	
	
	
	


}
