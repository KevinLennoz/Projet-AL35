package fr.eql.al35.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.User;
import fr.eql.al35.service.AccountIService;
import fr.eql.al35.service.ProductIService;

@Controller
@SessionAttributes({"sessionCart", "sessionUser"})
public class AccountController {
	
	@Autowired
	private ProductIService productService;
	
	@Autowired
	private AccountIService accountService;
	
	
	@GetMapping({"/", "/home"})
	public String testPage(Model model, HttpSession session) {
		
		//Utilisateur 3 en dur en session (pour ne pas avoir à créer de compte)
		User user3 = accountService.getUser3();
		model.addAttribute("sessionUser", user3);
		
		//Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		
		Cart sessionCart = productService.generateCartDatas(); //TODO A retirer une fois le programme fonctionnel
	
		if(sessionCart == null) {
			Cart cart = new Cart();
			cart.setArticlesQuantity(0);
			model.addAttribute("sessionCart", cart);
		} else {
			model.addAttribute("sessionCart", sessionCart);
			for (Article a : sessionCart.getArticles()) {
				sessionCart.setArticlesQuantity(sessionCart.getArticlesQuantity() + a.getQuantity());
			}
		}

		return "home";
	}
}
