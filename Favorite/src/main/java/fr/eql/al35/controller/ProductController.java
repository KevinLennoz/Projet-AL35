package fr.eql.al35.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.CommandArticle;
import fr.eql.al35.entity.Custom;
import fr.eql.al35.entity.Location;
import fr.eql.al35.entity.ProductType;
import fr.eql.al35.entity.ProductTypeLocation;
import fr.eql.al35.service.ProductIService;
import fr.eql.al35.service.ProductTypeLocationIService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductIService productService;
	
	@Autowired
	private ProductTypeLocationIService PTLService;

	@GetMapping("/products/all")
	public String displayAllProducts(Model model) {
		model.addAttribute("products", productService.displayAvailableProducts());
		model.addAttribute("categories", productService.displayAllCategories());
		return "showcase";
	}
	
	@GetMapping("/products/{category}/{id}")
	public String displayProduct(@PathVariable String category, @PathVariable Integer id, Model model) {
		//TO DO : faire proprement avec les repo
		CommandArticle commandArticle = new CommandArticle();
		Article article = new Article();
		article.setProduct(productService.displayProductById(id));
		Location location = new Location();
		//modif propre à faire :
		location.setLabel("centre avant");
		ProductTypeLocation productTypeLocation = new ProductTypeLocation();
		productTypeLocation.setLocation(location);
		Custom custom1 = new Custom(1, 5.9, productTypeLocation, null, null); 
		Set<Custom> customs = new HashSet<Custom>();
		customs.add(custom1);
		article.setCustoms(customs);
		commandArticle.setArticle(article);
		model.addAttribute("product", productService.displayProductById(id));
		model.addAttribute("designs", productService.displayAllDesign());
		model.addAttribute("productTypeLocation", PTLService.displayAllProductTypeLocation());
		model.addAttribute("categories", productService.displayAllCategories());
		model.addAttribute("commandArticle", commandArticle);
		model.addAttribute("productPhotos", article.getProduct().getPhotos());
		model.addAttribute("quantity");

		return "productSheet";
	}
	
	@GetMapping("/products/{productType}")
	public String displayProductsByType(@PathVariable ProductType productType, Model model) {
		model.addAttribute("categories", productService.displayAllCategories());
		model.addAttribute("products", productService.displayByProductType(productType));
		return "showcase";
	}


}
