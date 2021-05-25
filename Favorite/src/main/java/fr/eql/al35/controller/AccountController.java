package fr.eql.al35.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.CommandArticle;
import fr.eql.al35.service.CartIService;
import fr.eql.al35.service.ProductIService;

@Controller
@SessionAttributes({"sessionCart"})
public class AccountController {
	
//	@Autowired
//	private CartIService cartService;
	
	@Autowired
	private ProductIService productService;
	
	
	@GetMapping({"/", "/home"})
	public String testPage(Model model, HttpSession session) {
		
		//Cart cart = (Cart) session.getAttribute("sessionCart");
		
		Cart cart = new Cart();
		Article article = new Article();
		Article article2 = new Article();
		Article article3 = new Article();
		article.setProduct(productService.displayProductById(2));
		article2.setProduct(productService.displayProductById(3));
		article3.setProduct(productService.displayProductById(4));
		Set<CommandArticle> commandArticles = new HashSet<CommandArticle>();
		CommandArticle item1 = new CommandArticle(1, 5, null, null, article); //mettre une taille quand import finit
		CommandArticle item2 = new CommandArticle(2, 4, null, null, article2);
		CommandArticle item3 = new CommandArticle(3, 6, null, null, article3);
		commandArticles.addAll(Arrays.asList(item1, item2, item3));
		cart.setCommandArticles(commandArticles);
		System.out.println(cart.getCommandArticles());
		
		if(cart == null) {
			model.addAttribute("sessionCart", new Cart());
			cart.setArticlesQuantity(0);
		}else {
			model.addAttribute("sessionCart", cart);
			for (CommandArticle a : cart.getCommandArticles()) {
				cart.setArticlesQuantity(cart.getArticlesQuantity()+a.getQuantity());
			}
		}

		return "home";
	}
}
