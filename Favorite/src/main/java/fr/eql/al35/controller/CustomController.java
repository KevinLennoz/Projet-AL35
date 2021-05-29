package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Size;
import fr.eql.al35.iservice.DesignIService;
import fr.eql.al35.iservice.ProductIService;

@Controller
public class CustomController {
	
	@Autowired
	private ProductIService productService;
	@Autowired
	private DesignIService designService;
	private Size size;
	private Integer quantity;
	
	@PostMapping("/generateCustom")
	public String displayGenrateCustom(@ModelAttribute("article") Article article, @RequestParam("idProduct") Integer idProduct,
			 Model model) {
		
		String category = productService.displayProductById(idProduct).getProductType().getName();
		size = article.getSize();
		quantity = article.getQuantity();

		return "redirect:/custom/"+category+"/"+idProduct;
	}
	
	@GetMapping("/custom/{category}/{id}")
	public String displayCustom(@PathVariable String category, @PathVariable Integer id, Model model) {
		
		Article article = new Article();
		article.setSize(size);
		article.setQuantity(quantity);
		model.addAttribute("product", productService.displayProductById(id));
		model.addAttribute("categories", productService.displayAllCategories());
		model.addAttribute("designs",designService.displayAllDesign());
		model.addAttribute("article", article);
		
		return "custom";
	}
}
