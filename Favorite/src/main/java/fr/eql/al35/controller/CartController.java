package fr.eql.al35.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Cart;
import fr.eql.al35.service.CartIService;

@Controller
@SessionAttributes({"sessionCart"})
public class CartController {
	

	@Autowired
	private CartIService cartService;
	
	@PostMapping("/addToCart")
	public String displayAllProducts(@ModelAttribute("article") Article article,
									 Model model,
									 HttpSession session) {
		System.out.println(article);
//		if (!cartService.enoughInStock(article, article.getArticle().getProduct())) {
//			return "/addToCart";
//		}
		
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		
		cartService.addArticle(sessionCart, article);
		
		model.addAttribute("sessionCart", sessionCart);
		System.out.println(article);
		return "redirect:/products/all";
	}
	
	@GetMapping("/cart")
	public String displayCartProduct( Model model,
									 HttpSession session) {
		System.out.println("je suis dans displayCartProduct");
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		Set<Article> articles = sessionCart.getArticles();
		//List<Product> listProducts = new ArrayList<Product>();
		//List<Integer> quantity = new ArrayList();
		/*for (Article article : articles) {
			listProducts.add(article.getArticle().getProduct());
			quantity.add(article.getQuantity());
		}*/
		double total = cartService.getTotalPriceCart(sessionCart);
		sessionCart.setPrice(total);
		System.out.println(total);
	
		model.addAttribute("total", total); //Flo -> Mathilda : peut etre à retirer maintenant que Cart a un price en attribut
		model.addAttribute("articles", articles);
		//model.addAttribute("quantity", quantity);
	

		return "cart";
	}



}
