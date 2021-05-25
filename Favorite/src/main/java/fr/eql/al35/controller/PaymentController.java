package fr.eql.al35.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;
import fr.eql.al35.entity.CommandArticle;

import org.springframework.ui.Model;

@Controller
//@SessionAttributes({"sessionCart", "articlesQuantity"})
public class PaymentController {

	@GetMapping("/payment")
	public String displayPayment(Model model, HttpSession session) {
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		
		// en cours de dev
		return "payment";
	}

	@PostMapping("/newCommand")
	public String createNewCommand(@ModelAttribute("command") Command command, Model model, HttpSession session) {
		return null;
	}

}
