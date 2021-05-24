package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.CommandArticle;
import fr.eql.al35.service.ProductIService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductIService productService;

	@GetMapping("/products/all")
	public String displayAllProducts(Model model) {
		model.addAttribute("products", productService.displayAvailableProducts());
		model.addAttribute("categories", productService.displayAllCategories());
		return "showcase";
	}
	
	@GetMapping("/products/{category}/{id}")
	public String displayProduct(@PathVariable String category, @PathVariable Integer id, Model model) {
		
		CommandArticle commandArticle = new CommandArticle();
		Article article = new Article();
		article.setProduct(productService.displayProductById(id));
		commandArticle.setArticle(article);

		model.addAttribute("commandArticle", commandArticle);
		model.addAttribute("productPhotos", article.getProduct().getPhotos());
		model.addAttribute("quantity");
		
		return "productSheet";
	}
	
	@GetMapping("/products/veste")
	public String displayVesteProducts(Model model) {
		model.addAttribute("products", productService.displayProductVeste());
		return "vestes";
	}

}
