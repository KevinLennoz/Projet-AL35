package fr.eql.al35.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Cart;
import fr.eql.al35.iservice.ArticleIService;
import fr.eql.al35.iservice.CartIService;

@Controller
@SessionAttributes({"sessionCart"})
public class CartController {
	

	@Autowired
	private CartIService cartService;
	@Autowired
	private ArticleIService articleService;
	

	@PostMapping("/addToCart")
	public String displayAllProducts(@ModelAttribute("article") Article article, @RequestParam("idProduct") Integer idProduct,
									 Model model,
									 HttpSession session) {
		
		articleService.addProduit(idProduct, article);
	
		if(!cartService.enoughInStock(article, article.getProduct())){
			return "plusDeStock";
		}
		
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		cartService.addArticle(sessionCart, article);
		
		return "redirect:/products/all";
	}
	
	@GetMapping("/cart")
	public String displayCartProduct( Model model,
									 HttpSession session) {
		
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		Set<Article> articles = sessionCart.getArticles();
		model.addAttribute("articles", articles);
		model.addAttribute("total", sessionCart.getPrice());
		return "cart";
	}



}
