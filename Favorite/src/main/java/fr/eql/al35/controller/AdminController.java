package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eql.al35.entity.Product;
import fr.eql.al35.service.ProductIService;

@Controller
@SessionAttributes({"sessionCart"})
public class AdminController {

	@Autowired
	ProductIService productService;

	/*@PostMapping("/addToCart")
	public String displayAllProducts(@ModelAttribute("commandArticle") CommandArticle commandArticle,
			Model model,
			HttpSession session) {

		Cart sessionCart = (Cart) session.getAttribute("sessionCart");

		sessionCart.getCommandArticles().add(commandArticle);
		sessionCart.setArticlesQuantity(sessionCart.getArticlesQuantity()+commandArticle.getQuantity());

		model.addAttribute("sessionCart", sessionCart);
		return "redirect:/products/all";
	}*/

	@GetMapping("/admin/product")
	public String displayCartProduct( Model model) {
		model.addAttribute("products", productService.displayAllProducts());
		return "adminProduct";
	}
	
	@GetMapping("/admin/home")
	public String redirectAdminHome( Model model) {
		return "adminHome";
	}
	
	@PostMapping("/upDateProducts")
	public String upDateProducts(@ModelAttribute("product")Product product, Model model) {
		System.out.println("================================" + product.toString());
		
		productService.upDateProduct(product);
		System.out.println("================================" + product.toString());
		model.addAttribute("products", productService.displayAllProducts());

		/*sessionCart.getCommandArticles().add(commandArticle);
		sessionCart.setArticlesQuantity(sessionCart.getArticlesQuantity()+commandArticle.getQuantity());

		model.addAttribute("sessionCart", sessionCart);*/
		return "adminProduct";
	}



}
