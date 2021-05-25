package fr.eql.al35.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.CommandArticle;
import fr.eql.al35.entity.Product;
import fr.eql.al35.service.CartIService;

@Controller
@SessionAttributes({"sessionCart"})
public class CartController {
	

	@Autowired
	private CartIService cartService;
	
	@PostMapping("/addToCart")
	public String displayAllProducts(@ModelAttribute("commandArticle") CommandArticle commandArticle,
									 Model model,
									 HttpSession session) {
		
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		
		sessionCart.getCommandArticles().add(commandArticle);
		sessionCart.setArticlesQuantity(sessionCart.getArticlesQuantity()+commandArticle.getQuantity());
		
		model.addAttribute("sessionCart", sessionCart);
		return "redirect:/products/all";
	}
	
	@GetMapping("/cart")
	public String displayCartProduct( Model model,
									 HttpSession session) {
		System.out.println("je suis dans displayCartProduct");
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		Set<CommandArticle> commandArticles = sessionCart.getCommandArticles();
		//List<Product> listProducts = new ArrayList<Product>();
		//List<Integer> quantity = new ArrayList();
		/*for (CommandArticle commandArticle : commandArticles) {
			listProducts.add(commandArticle.getArticle().getProduct());
			quantity.add(commandArticle.getQuantity());
		}*/
		double total = cartService.getTotalPriceCart(sessionCart);
		sessionCart.setPrice(total);
		System.out.println(total);
	
		
		model.addAttribute("total", total); //Flo -> Mathilda : peut etre à retirer maintenant que Cart a un price en attribut
		model.addAttribute("commandArticles", commandArticles);
		//model.addAttribute("quantity", quantity);
	

		return "cart";
	}



}
