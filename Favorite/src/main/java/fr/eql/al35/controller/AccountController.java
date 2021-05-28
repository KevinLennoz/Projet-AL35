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
import fr.eql.al35.iservice.AccountIService;
import fr.eql.al35.iservice.ProductIService;

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
		
		Cart cart = new Cart();
		cart.setArticlesQuantity(0);
		cart.setPrice(0.0);
		model.addAttribute("sessionCart", cart);
		
		return "home";
	}
	
	@GetMapping("/switchAdmin")
	public String switchAdminAccount(Model model, HttpSession session) {
		
		session.invalidate();
		User admin = accountService.getAdminAccount();
		model.addAttribute("sessionUser", admin);
		
		return "adminHome";
	}
}
