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
	public String displayHome(Model model) {
		
		//Utilisateur 3 en dur en session (pour ne pas avoir à créer de compte)
		User user3 = accountService.getUser3();
		model.addAttribute("sessionUser", user3);
		
		Cart sessionCart = productService.generateCartDatas();
		sessionCartGenerator(model, sessionCart);

		return "home";
	}
	
	@GetMapping("/switchAdmin")
	public String switchAdminAccount(Model model, HttpSession session) {
		
		User admin = accountService.getAdminAccount();
		model.addAttribute("sessionUser", admin);
		Cart sessionCart = new Cart();
		sessionCartGenerator(model, sessionCart);
		User user = (User) session.getAttribute("sessionUser");
		System.out.println(user.getUserType());
		
		return "adminHome";
	}
	
	@GetMapping("/switchUser")
	public String switchUser3Account(Model model, HttpSession session) {
		
		User user3 = accountService.getUser3();
		model.addAttribute("sessionUser", user3);
		
		Cart sessionCart = productService.generateCartDatas();
		sessionCartGenerator(model, sessionCart);
		
		return "home";
	}
	
	private void sessionCartGenerator(Model model, Cart sessionCart) {
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
	}
}
