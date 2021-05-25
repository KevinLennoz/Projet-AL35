package fr.eql.al35.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;
import fr.eql.al35.service.CommandIService;

import org.springframework.ui.Model;

@Controller
//@SessionAttributes({"sessionCart", "articlesQuantity"})
public class PaymentController {
	
	@Autowired
	CommandIService cmdService;

	@GetMapping("/payment")
	public String displayPayment(Model model, HttpSession session) {
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		Command command = cmdService.convertCartToCommand(sessionCart);
		model.addAttribute("command", command); //pour l'avoir dans le post
		return "payment";
	}

	@PostMapping("/newCommand") 
	public String createNewCommand(@ModelAttribute("command") Command command, Model model) {
		//*****
		//aller recup infos dans l'html : 
		//*****
		command.setReference(writeReference(command));
		cmdService.createCommand(command);
		return "payment";
	}
	
	private String writeReference(Command command) {
		StringBuilder reference = new StringBuilder();
		reference.append("CMD_");
		reference.append(command.getCreationDate().toString());
		reference.append("_Client");
		reference.append("essaiNUMEROCLIENT"); //a modif avec le n° Client en session
		return reference.toString();
	}

}
