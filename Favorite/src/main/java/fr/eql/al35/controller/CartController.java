package fr.eql.al35.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.CommandArticle;

@Controller
@SessionAttributes({"sessionCart"})
public class CartController {
	

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

}
