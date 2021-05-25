package fr.eql.al35.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class PaymentController {
	
	@GetMapping("/payment")
	public String displayPayment(Model model) {
		return "payment";
	}

}
